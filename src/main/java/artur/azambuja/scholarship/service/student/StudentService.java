package artur.azambuja.scholarship.service.student;

import artur.azambuja.scholarship.dto.student.StudentRequestDTO;
import artur.azambuja.scholarship.dto.student.StudentResponseDTO;
import artur.azambuja.scholarship.model.Student;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends serviceClass {
    public StudentService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public StudentResponseDTO convertStudentToResponseDTO(Student student) {
        return modelMapper.map(student, StudentResponseDTO.class);
    }
    public Student convertStudentRequestDTOToEntity(StudentRequestDTO dto) {
        return modelMapper.map(dto, Student.class);
    }
}
