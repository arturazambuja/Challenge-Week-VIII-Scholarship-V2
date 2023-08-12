package artur.azambuja.scholarship.service.classroom;

import artur.azambuja.scholarship.dto.classroom.ClassroomRequestDTO;
import artur.azambuja.scholarship.dto.classroom.ClassroomResponseDTO;
import artur.azambuja.scholarship.exceptions.Instructor.InsufficientInstructorsException;
import artur.azambuja.scholarship.exceptions.classroom.ClassroomNotFoundException;
import artur.azambuja.scholarship.exceptions.classroom.InsufficientInternalException;
import artur.azambuja.scholarship.model.*;
import artur.azambuja.scholarship.repository.classroom.ClassroomRepository;
import artur.azambuja.scholarship.repository.coordinator.CoordinatorRepository;
import artur.azambuja.scholarship.repository.instructor.InstructorRepository;
import artur.azambuja.scholarship.repository.scrumMaster.ScrumMasterRepository;
import artur.azambuja.scholarship.repository.squad.SquadRepository;
import artur.azambuja.scholarship.repository.student.StudentRepository;
import artur.azambuja.scholarship.service.serviceClass;
import artur.azambuja.scholarship.service.squad.SquadService;
import artur.azambuja.scholarship.service.student.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomService extends serviceClass {
    public ClassroomResponseDTO convertClassroomToResponseDTO(Classroom classroom) {
        return modelMapper.map(classroom, ClassroomResponseDTO.class);
    }
    public Classroom convertClassroomRequestDTOToEntity(ClassroomRequestDTO dto) {
        return modelMapper.map(dto, Classroom.class);
    }

    private final ClassroomRepository classroomRepository;
    private final CoordinatorRepository coordinatorRepository;
    private final InstructorRepository instructorRepository;
    private final ScrumMasterRepository scrumMasterRepository;
    private final SquadRepository squadRepository;
    private final StudentRepository studentRepository;
    private final SquadService squadService;
    private final StudentService studentService;
    @Autowired
    public ClassroomService(ModelMapper modelMapper, ClassroomRepository classroomRepository, CoordinatorRepository coordinatorRepository, InstructorRepository instructorRepository, ScrumMasterRepository scrumMasterRepository, SquadRepository squadRepository, StudentRepository studentRepository, SquadService squadService, StudentService studentService){
        super(modelMapper);
        this.classroomRepository = classroomRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.instructorRepository = instructorRepository;
        this.scrumMasterRepository = scrumMasterRepository;
        this.squadRepository = squadRepository;
        this.studentRepository = studentRepository;
        this.squadService = squadService;
        this.studentService = studentService;
    }
    public ClassroomResponseDTO createClassroom(ClassroomRequestDTO requestDTO) throws InsufficientInstructorsException {

        Classroom classroom = new Classroom();
        classroom.setClassroom(requestDTO.getClassroom());

        Coordinator coordinator = findCoordinator();
        ScrumMaster scrumMaster = findScrumMaster();
        List<Instructor> instructors = findInstructors(3);

        if (coordinator == null || scrumMaster == null || instructors.size() < 3) {
            try {
                throw new InsufficientInternalException("Insufficient resources to create classroom");
            } catch (InsufficientInternalException e) {
                throw new RuntimeException(e);
            }
        }

        classroom.setCoordinator(coordinator);
        classroom.setScrumMaster(scrumMaster);
        classroom.setInstructors(instructors);

        Classroom savedClassroom = classroomRepository.save(classroom);

        return convertClassroomToResponseDTO(savedClassroom);
    }
    private Coordinator findCoordinator(){
        return coordinatorRepository.findAnyCoordinator();
    }
    private ScrumMaster findScrumMaster(){
        return scrumMasterRepository.findAnyScrumMaster();
    }
    private List<Instructor> findInstructors(int count) throws InsufficientInstructorsException {
        List<Instructor> instructors = instructorRepository.findAnyInstructors(count);

        if(instructors.size() < count) {
            throw new InsufficientInstructorsException("Insufficient number of instructors");
        }
        return instructors;
    }
    public void startClassroom(Long classroomId) throws ClassroomNotFoundException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException("Classroom not found with this id"));

        classroom.setStatus("started");
        classroomRepository.save(classroom);

        List<Student> students = studentService.getStudentsByClassroomId(classroom.getIdClassroom());
        squadService.createAndDistributeSquads(classroom, students);
    }
    public void finishClassroom(Long classroomId) throws ClassroomNotFoundException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException("Classroom not found with this id"));

        classroom.setStatus("finished");
        classroomRepository.save(classroom);
    }
    public List<ClassroomResponseDTO> getAllClassrooms() {
        List<Classroom> classrooms = classroomRepository.findAll();
        return classrooms.stream()
                .map(this::convertClassroomToResponseDTO)
                .collect(Collectors.toList());
    }

    public ClassroomResponseDTO getClassroomById(Long classroomId) throws ClassroomNotFoundException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException("Classroom not found with this id"));
        return convertClassroomToResponseDTO(classroom);
    }

    public ClassroomResponseDTO updateClassroom(Long classroomId, ClassroomRequestDTO requestDTO) throws ClassroomNotFoundException {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException("Classroom not found with this id"));

        classroom.setClassroom(requestDTO.getClassroom());
        classroom.setStatus(requestDTO.getStatus());
        classroom.setStudents(requestDTO.getStudents());

        classroomRepository.save(classroom);
        return convertClassroomToResponseDTO(classroom);
    }
}