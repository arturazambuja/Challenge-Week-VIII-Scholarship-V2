package artur.azambuja.scholarship.service.student;

import artur.azambuja.scholarship.dto.student.StudentRequestDTO;
import artur.azambuja.scholarship.dto.student.StudentResponseDTO;
import artur.azambuja.scholarship.exceptions.classroom.ClassroomFullException;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Student;
import artur.azambuja.scholarship.repository.classroom.ClassroomRepository;
import artur.azambuja.scholarship.repository.student.StudentRepository;
import artur.azambuja.scholarship.service.serviceClass;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends serviceClass {
    public StudentService(ModelMapper modelMapper, StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        super(modelMapper);
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }
    public StudentResponseDTO convertStudentToResponseDTO(Student student) {
        return modelMapper.map(student, StudentResponseDTO.class);
    }
    public Student convertStudentRequestDTOToEntity(StudentRequestDTO dto) {
        return modelMapper.map(dto, Student.class);
    }
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;
    public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) throws ClassroomFullException {

        Classroom classroom = classroomRepository.findById(requestDTO.getIdClassroom())
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found with this id"));

        if (!classroom.isAcceptingNewStudents()) {
            throw new ClassroomFullException("Classroom is not accepting new students");
        }

        Student student = new Student();
        student.setFirstName(requestDTO.getFirstName());
        student.setLastName(requestDTO.getLastName());
        student.setEmail(requestDTO.getEmail());
        student.setClassroom(classroom);

        Student savedStudent = studentRepository.save(student);

        return convertStudentToResponseDTO(savedStudent);
    }
    public List<Student> getStudentsByClassroomId(Long classroomId) {
        return studentRepository.findByClassroom_IdClassroom(classroomId);
    }
}
