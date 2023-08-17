package artur.azambuja.scholarship.service.instructor;

import artur.azambuja.scholarship.dto.instructor.InstructorRequestDTO;
import artur.azambuja.scholarship.dto.instructor.InstructorResponseDTO;
import artur.azambuja.scholarship.exceptions.EmailAlreadyRegistredException;
import artur.azambuja.scholarship.exceptions.Instructor.InstructorNotFoundException;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Instructor;
import artur.azambuja.scholarship.repository.classroom.ClassroomRepository;
import artur.azambuja.scholarship.repository.instructor.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class InstructorServiceTest {
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private InstructorService instructorService;
    @Mock
    private InstructorRepository instructorRepository;
    @Mock
    private ClassroomRepository classroomRepository;

    @Test
    void convertInstructorToResponseDTO() {

        Instructor instructor = mock(Instructor.class);

        ModelMapper modelMapper = new ModelMapper();

        InstructorResponseDTO responseDTO = modelMapper.map(instructor, InstructorResponseDTO.class);

        assertEquals(responseDTO.getFirstName(), instructor.getFirstName());
        assertEquals(responseDTO.getLastName(), instructor.getLastName());
        assertEquals(responseDTO.getEmail(), instructor.getEmail());
    }

    @Test
    void convertInstructorRequestDTOToEntity() {
        InstructorRequestDTO requestDTO = new InstructorRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");
        requestDTO.setEmail("john.doe@example.com");

        Instructor instructor = modelMapper.map(requestDTO, Instructor.class);

        assertEquals(instructor.getFirstName(), requestDTO.getFirstName());
        assertEquals(instructor.getLastName(), requestDTO.getLastName());
        assertEquals(instructor.getEmail(), requestDTO.getEmail());
    }

    @Test
    void createInstructor() throws EmailAlreadyRegistredException {

        InstructorRequestDTO requestDTO = new InstructorRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");
        requestDTO.setEmail("john.doe@example.com");

        when(instructorRepository.existsByEmail("john.doe@example.com")).thenReturn(false);

        when(instructorRepository.save(any(Instructor.class))).thenAnswer((Answer<Instructor>) invocation -> {
            Instructor savedInstructor = invocation.getArgument(0);
            savedInstructor.setIdInstructor(1L); // Set an example ID
            return savedInstructor;
        });

        InstructorResponseDTO responseDTO = instructorService.createInstructor(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("John", responseDTO.getFirstName());
        assertEquals("Doe", responseDTO.getLastName());
        assertEquals("john.doe@example.com", responseDTO.getEmail());
    }

    @Test
    void getAllInstructors() {
        List<Instructor> mockInstructors = new ArrayList<>();
        Instructor instructor1 = new Instructor();
        instructor1.setIdInstructor(1L);
        instructor1.setFirstName("John");
        instructor1.setLastName("Doe");
        instructor1.setEmail("john.doe@example.com");
        Instructor instructor2 = new Instructor();
        instructor2.setIdInstructor(2L);
        instructor2.setFirstName("Jane");
        instructor2.setLastName("Smith");
        instructor2.setEmail("jane.smith@example.com");
        mockInstructors.add(instructor1);
        mockInstructors.add(instructor2);

        when(instructorRepository.findAll()).thenReturn(mockInstructors);

        List<InstructorResponseDTO> responseDTOs = instructorService.getAllInstructors();

        assertNotNull(responseDTOs);
        assertEquals(2, responseDTOs.size());

        InstructorResponseDTO responseDTO1 = responseDTOs.get(0);
        assertEquals("John", responseDTO1.getFirstName());
        assertEquals("Doe", responseDTO1.getLastName());
        assertEquals("john.doe@example.com", responseDTO1.getEmail());

        InstructorResponseDTO responseDTO2 = responseDTOs.get(1);
        assertEquals("Jane", responseDTO2.getFirstName());
        assertEquals("Smith", responseDTO2.getLastName());
        assertEquals("jane.smith@example.com", responseDTO2.getEmail());
    }

    @Test
    void getInstructorById() throws InstructorNotFoundException {

        Instructor mockInstructor = new Instructor();
        mockInstructor.setIdInstructor(1L);
        mockInstructor.setFirstName("John");
        mockInstructor.setLastName("Doe");
        mockInstructor.setEmail("john.doe@example.com");

        when(instructorRepository.findById(1L)).thenReturn(Optional.of(mockInstructor));

        InstructorResponseDTO responseDTO = instructorService.getInstructorById(1L);

        assertNotNull(responseDTO);
        assertEquals("John", responseDTO.getFirstName());
        assertEquals("Doe", responseDTO.getLastName());
        assertEquals("john.doe@example.com", responseDTO.getEmail());
    }

    @Test
    public void testGetInstructorByIdNotFound() {

        when(instructorRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(InstructorNotFoundException.class, () -> {
            instructorService.getInstructorById(2L);
        });
    }

    @Test
    void updateInstructor() throws InstructorNotFoundException {

        Instructor instructor = new Instructor();
        instructor.setIdInstructor(1L);
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setEmail("john.doe@example.com");

        InstructorRequestDTO requestDTO = new InstructorRequestDTO();
        requestDTO.setFirstName("UpdatedJohn");
        requestDTO.setLastName("UpdatedDoe");
        requestDTO.setEmail("updated.john.doe@example.com");

        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        InstructorResponseDTO responseDTO = instructorService.updateInstructor(1L, requestDTO);

        assertNotNull(responseDTO);
        assertEquals("UpdatedJohn", responseDTO.getFirstName());
        assertEquals("UpdatedDoe", responseDTO.getLastName());
        assertEquals("updated.john.doe@example.com", responseDTO.getEmail());

        verify(instructorRepository).save(instructor);
    }

    @Test
    public void testUpdateInstructorNotFound() {

        when(instructorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(InstructorNotFoundException.class,
                () -> instructorService.updateInstructor(1L, new InstructorRequestDTO()));
    }


    @Test
    void deleteInstructor() {
    }

    @Test
    public void testDeleteInstructorNotFound() {

        when(instructorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(InstructorNotFoundException.class,
                () -> instructorService.deleteInstructor(1L));

        verify(instructorRepository, never()).delete(any());
        verify(classroomRepository, never()).save(any());
    }

}