package artur.azambuja.scholarship.service.classroom;

import artur.azambuja.scholarship.dto.classroom.ClassroomRequestDTO;
import artur.azambuja.scholarship.dto.classroom.ClassroomResponseDTO;
import artur.azambuja.scholarship.exceptions.Instructor.InsufficientInstructorsException;
import artur.azambuja.scholarship.exceptions.classroom.ClassroomNotFoundException;
import artur.azambuja.scholarship.exceptions.student.InsufficientStudentsException;
import artur.azambuja.scholarship.model.*;
import artur.azambuja.scholarship.repository.classroom.ClassroomRepository;
import artur.azambuja.scholarship.repository.coordinator.CoordinatorRepository;
import artur.azambuja.scholarship.repository.instructor.InstructorRepository;
import artur.azambuja.scholarship.repository.scrumMaster.ScrumMasterRepository;
import artur.azambuja.scholarship.service.squad.SquadService;
import artur.azambuja.scholarship.service.student.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ClassroomServiceTest {

    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private ClassroomService classroomService;
    @Mock
    private ClassroomRepository classroomRepository;
    @Mock
    private StudentService studentService;
    @Mock
    private SquadService squadService;
    @Mock
    private CoordinatorRepository coordinatorRepository;
    @Mock
    private ScrumMasterRepository scrumMasterRepository;
    @Mock
    private InstructorRepository instructorRepository;
    @Test
    void convertClassroomToResponseDTO() {
        Classroom classroom = new Classroom();
        classroom.setIdClassroom(1L);
        classroom.setClassroom("Math Class");

        ClassroomResponseDTO mockResponseDTO = new ClassroomResponseDTO();
        mockResponseDTO.setIdClassroom(1L);
        mockResponseDTO.setClassroom("Math Class");

        when(modelMapper.map(classroom, ClassroomResponseDTO.class)).thenReturn(mockResponseDTO);

        ClassroomResponseDTO responseDTO = classroomService.convertClassroomToResponseDTO(classroom);

        assertNotNull(responseDTO);
        assertEquals(mockResponseDTO.getIdClassroom(), responseDTO.getIdClassroom());
        assertEquals(mockResponseDTO.getClassroom(), responseDTO.getClassroom());

        verify(modelMapper, times(1)).map(classroom, ClassroomResponseDTO.class);
    }

    @Test
    void convertClassroomRequestDTOToEntity() {
        ClassroomRequestDTO requestDTO = new ClassroomRequestDTO();
        requestDTO.setClassroom("Math Class");
        requestDTO.setStatus("Active");

        Classroom classroom = classroomService.convertClassroomRequestDTOToEntity(requestDTO);

        assertEquals(requestDTO.getClassroom(), classroom.getClassroom());
        assertEquals(requestDTO.getStatus(), classroom.getStatus());
    }

    @Test
    void createClassroom()throws InsufficientStudentsException, InsufficientInstructorsException {

        List<Coordinator> coordinators = new ArrayList<>();
        when(coordinatorRepository.findAnyCoordinator()).thenReturn(coordinators);

        List<ScrumMaster> scrumMasters = new ArrayList<>();
        when(scrumMasterRepository.findAnyScrumMaster()).thenReturn(scrumMasters);

        List<Instructor> instructors = new ArrayList<>();
        when(instructorRepository.findAnyInstructors(anyInt())).thenReturn(instructors);

        List<Student> students = new ArrayList<>();
        when(studentService.findAvailableStudentsForClassroom()).thenReturn(students);


        when(coordinatorRepository.findAnyCoordinator()).thenReturn(new ArrayList<>());
        when(scrumMasterRepository.findAnyScrumMaster()).thenReturn(new ArrayList<>());
        when(instructorRepository.findAnyInstructors(anyInt())).thenReturn(new ArrayList<>());


        try {
            classroomService.createClassroom(new ClassroomRequestDTO());
        } catch (InsufficientInstructorsException e) {
            return;
        }

        fail("Expected InsufficientInstructorsException but no exception was thrown");
    }

    @Test
    void startClassroom() throws ClassroomNotFoundException {

        Classroom mockClassroom = new Classroom();
        mockClassroom.setIdClassroom(1L);
        mockClassroom.setStatus("not_started");

        List<Student> mockStudents = new ArrayList<>();
        Student student1 = new Student();
        student1.setIdStudent(1L);
        Student student2 = new Student();
        student2.setIdStudent(2L);
        mockStudents.add(student1);
        mockStudents.add(student2);

        when(classroomRepository.findById(1L)).thenReturn(Optional.of(mockClassroom));
        when(studentService.getStudentsByClassroomId(1L)).thenReturn(mockStudents);

        classroomService.startClassroom(1L);

        assertEquals("started", mockClassroom.getStatus());

        verify(squadService, times(1)).createAndDistributeSquads(mockClassroom, mockStudents);

        verify(classroomRepository, times(1)).save(mockClassroom);
    }

    @Test
    void finishClassroom() throws ClassroomNotFoundException {

        Classroom mockClassroom = new Classroom();
        mockClassroom.setIdClassroom(1L);
        mockClassroom.setStatus("started");

        when(classroomRepository.findById(1L)).thenReturn(Optional.of(mockClassroom));

        classroomService.finishClassroom(1L);

        assertEquals("finished", mockClassroom.getStatus());

        verify(classroomRepository, times(1)).save(mockClassroom);
    }

    @Test
    void getAllClassrooms() {
        Classroom classroom1 = new Classroom();
        classroom1.setIdClassroom(1L);
        classroom1.setClassroom("Math");

        Classroom classroom2 = new Classroom();
        classroom2.setIdClassroom(2L);
        classroom2.setClassroom("Science");

        List<Classroom> mockClassrooms = Arrays.asList(classroom1, classroom2);

        when(classroomRepository.findAll()).thenReturn(mockClassrooms);

        List<ClassroomResponseDTO> result = classroomService.getAllClassrooms();

        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).getIdClassroom());
        assertEquals("Math", result.get(0).getClassroom());
        assertEquals(2L, result.get(1).getIdClassroom());
        assertEquals("Science", result.get(1).getClassroom());

        verify(classroomRepository, times(1)).findAll();
    }

    @Test
    void getClassroomById()throws ClassroomNotFoundException {

        Classroom classroom = new Classroom();
        classroom.setIdClassroom(1L);
        classroom.setClassroom("Math");

        when(classroomRepository.findById(1L)).thenReturn(Optional.of(classroom));

        ResponseEntity<ClassroomResponseDTO> result = classroomService.getClassroomById(1L);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        ClassroomResponseDTO responseDTO = result.getBody();
        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.getIdClassroom());
        assertEquals("Math", responseDTO.getClassroom());

        verify(classroomRepository, times(1)).findById(1L);
    }
    @Test
    @DisplayName("Test getClassroomById with ClassroomNotFoundException")
    public void testGetClassroomByIdNotFound() {

        when(classroomRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClassroomNotFoundException.class, () -> {
            classroomService.getClassroomById(1L);
        });
    }

    @Test
    void updateClassroom() throws ClassroomNotFoundException {
        Long classroomId = 1L;
        ClassroomRequestDTO requestDTO = new ClassroomRequestDTO();
        requestDTO.setClassroom("Updated Classroom");

        Classroom existingClassroom = new Classroom();
        existingClassroom.setIdClassroom(classroomId);

        when(classroomRepository.findById(classroomId)).thenReturn(Optional.of(existingClassroom));

        ClassroomResponseDTO updatedResponse = classroomService.updateClassroom(classroomId, requestDTO);

        verify(classroomRepository).save(existingClassroom);

        assertEquals(requestDTO.getClassroom(), updatedResponse.getClassroom());
    }

    @Test
    void testUpdateClassroomNotFound() {
        Long classroomId = 1L;
        ClassroomRequestDTO requestDTO = new ClassroomRequestDTO();
        requestDTO.setClassroom("Updated Classroom");

        when(classroomRepository.findById(classroomId)).thenReturn(Optional.empty());

        assertThrows(ClassroomNotFoundException.class, () -> {
            classroomService.updateClassroom(classroomId, requestDTO);
        });
    }
}