package artur.azambuja.scholarship.service.student;

import artur.azambuja.scholarship.dto.student.studentRequestDTO;
import artur.azambuja.scholarship.dto.student.studentResponseDTO;
import artur.azambuja.scholarship.model.Student;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class studentService extends serviceClass {
    public studentService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public studentResponseDTO convertStudentToResponseDTO(Student student) {
        return modelMapper.map(student, studentResponseDTO.class);
    }
    public Student convertStudentRequestDTOToEntity(studentRequestDTO dto) {
        return modelMapper.map(dto, Student.class);
    }
}
