package artur.azambuja.scholarship.service.student;

import artur.azambuja.scholarship.dto.student.StudentRequestDTO;
import artur.azambuja.scholarship.dto.student.StudentResponseDTO;
import artur.azambuja.scholarship.exceptions.classroom.ClassroomFullException;
import artur.azambuja.scholarship.exceptions.classroom.ClassroomNotFoundException;
import artur.azambuja.scholarship.exceptions.student.StudentNotFoundException;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Squad;
import artur.azambuja.scholarship.model.Student;
import artur.azambuja.scholarship.repository.classroom.ClassroomRepository;
import artur.azambuja.scholarship.repository.squad.SquadRepository;
import artur.azambuja.scholarship.repository.student.StudentRepository;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService extends serviceClass {
    public StudentService(ModelMapper modelMapper, StudentRepository studentRepository, ClassroomRepository classroomRepository, SquadRepository squadRepository) {
        super(modelMapper);
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
        this.squadRepository = squadRepository;
    }
    public StudentResponseDTO convertStudentToResponseDTO(Student student) {
        return modelMapper.map(student, StudentResponseDTO.class);
    }
    public Student convertStudentRequestDTOToEntity(StudentRequestDTO dto) {
        return modelMapper.map(dto, Student.class);
    }
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;
    private final SquadRepository squadRepository;
    public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) throws ClassroomFullException, ClassroomNotFoundException {

        Classroom classroom = classroomRepository.findById(requestDTO.getIdClassroom())
                .orElseThrow(() -> new ClassroomNotFoundException("Classroom not found with this id"));

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
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertStudentToResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with this id"));
        return convertStudentToResponseDTO(student);
    }

    public StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO requestDTO) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with this id"));

        student.setFirstName(requestDTO.getFirstName());
        student.setLastName(requestDTO.getLastName());
        student.setEmail(requestDTO.getEmail());

        studentRepository.save(student);
        return convertStudentToResponseDTO(student);
    }
    public void deleteStudent(Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with this id"));

        if (student.getClassroom() != null) {
            Classroom classroom = student.getClassroom();
            classroom.getStudents().remove(student);
            classroomRepository.save(classroom);
        }

        if (student.getSquad() != null) {
            Squad squad = student.getSquad();
            squad.getStudents().remove(student);
            squadRepository.save(squad);
        }

        studentRepository.delete(student);
    }
}
