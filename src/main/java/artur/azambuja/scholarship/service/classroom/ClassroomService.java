package artur.azambuja.scholarship.service.classroom;

import artur.azambuja.scholarship.dto.classroom.ClassroomRequestDTO;
import artur.azambuja.scholarship.dto.classroom.ClassroomResponseDTO;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Coordinator;
import artur.azambuja.scholarship.model.Instructor;
import artur.azambuja.scholarship.model.ScrumMaster;
import artur.azambuja.scholarship.repository.classroom.ClassroomRepository;
import artur.azambuja.scholarship.repository.coordinator.CoordinatorRepository;
import artur.azambuja.scholarship.repository.instructor.InstructorRepository;
import artur.azambuja.scholarship.repository.scrumMaster.ScrumMasterRepository;
import artur.azambuja.scholarship.repository.squad.SquadRepository;
import artur.azambuja.scholarship.repository.student.StudentRepository;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.Comparator;
import java.util.List;

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
    @Autowired
    public ClassroomService(ModelMapper modelMapper, ClassroomRepository classroomRepository, CoordinatorRepository coordinatorRepository, InstructorRepository instructorRepository, ScrumMasterRepository scrumMasterRepository, SquadRepository squadRepository, StudentRepository studentRepository){
        super(modelMapper);
        this.classroomRepository = classroomRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.instructorRepository = instructorRepository;
        this.scrumMasterRepository = scrumMasterRepository;
        this.squadRepository = squadRepository;
        this.studentRepository = studentRepository;
    }
    public ClassroomResponseDTO createClassroom(ClassroomRequestDTO requestDTO) {

        Classroom classroom = new Classroom();
        classroom.setClassroom(requestDTO.getClassroom());

        Coordinator coordinator = findAvailableCoordinator();
        ScrumMaster scrumMaster = findAvailableScrumMaster();
        List<Instructor> instructors = findAvailableInstructors(3);

        if (coordinator == null || scrumMaster == null || instructors.size() < 3) {
            try {
                throw new InsufficientResourcesException("Insufficient resources to create classroom");
            } catch (InsufficientResourcesException e) {
                throw new RuntimeException(e);
            }
        }

        classroom.setCoordinator(coordinator);
        classroom.setScrumMaster(scrumMaster);
        classroom.setInstructors(instructors);

        Classroom savedClassroom = classroomRepository.save(classroom);

        return convertClassroomToResponseDTO(savedClassroom);
    }
    private Coordinator findAvailableCoordinator(){
        return coordinatorRepository.findAnyCoordinator();
    }
    private ScrumMaster findAvailableScrumMaster(){
        return scrumMasterRepository.findAnyScrumMaster();
    }
    private List<Instructor> findAvailableInstructors(int count){
        List<Instructor> instructors = instructorRepository.findAnyInstructors(count);

        if(instructors.size() < count) {
            try {
                throw new InsufficientResourcesException("Insufficient number of instructors");
            } catch (InsufficientResourcesException e) {
                throw new RuntimeException(e);
            }
        }
        return instructors;
    }
}
