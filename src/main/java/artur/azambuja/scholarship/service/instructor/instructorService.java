package artur.azambuja.scholarship.service.instructor;

import artur.azambuja.scholarship.dto.instructor.instructorRequestDTO;
import artur.azambuja.scholarship.dto.instructor.instructorResponseDTO;
import artur.azambuja.scholarship.model.Instructor;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class instructorService extends serviceClass {
    public instructorService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public instructorResponseDTO convertInstructorToResponseDTO(Instructor instructor) {
        return modelMapper.map(instructor, instructorResponseDTO.class);
    }
    public Instructor convertInstructorRequestDTOToEntity(instructorRequestDTO dto) {
        return modelMapper.map(dto, Instructor.class);
    }
}
