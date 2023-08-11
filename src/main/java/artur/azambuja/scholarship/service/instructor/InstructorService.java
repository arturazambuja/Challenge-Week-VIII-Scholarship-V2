package artur.azambuja.scholarship.service.instructor;

import artur.azambuja.scholarship.dto.instructor.InstructorRequestDTO;
import artur.azambuja.scholarship.dto.instructor.InstructorResponseDTO;
import artur.azambuja.scholarship.model.Instructor;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class InstructorService extends serviceClass {
    public InstructorService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public InstructorResponseDTO convertInstructorToResponseDTO(Instructor instructor) {
        return modelMapper.map(instructor, InstructorResponseDTO.class);
    }
    public Instructor convertInstructorRequestDTOToEntity(InstructorRequestDTO dto) {
        return modelMapper.map(dto, Instructor.class);
    }
}
