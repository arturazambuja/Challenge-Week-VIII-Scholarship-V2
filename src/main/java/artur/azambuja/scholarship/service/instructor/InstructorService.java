package artur.azambuja.scholarship.service.instructor;

import artur.azambuja.scholarship.dto.instructor.InstructorRequestDTO;
import artur.azambuja.scholarship.dto.instructor.InstructorResponseDTO;
import artur.azambuja.scholarship.model.Instructor;
import artur.azambuja.scholarship.repository.instructor.InstructorRepository;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService extends serviceClass {
    public InstructorResponseDTO convertInstructorToResponseDTO(Instructor instructor) {
        return modelMapper.map(instructor, InstructorResponseDTO.class);
    }
    public Instructor convertInstructorRequestDTOToEntity(InstructorRequestDTO dto) {
        return modelMapper.map(dto, Instructor.class);
    }
    private final InstructorRepository instructorRepository;
    public InstructorService(ModelMapper modelMapper, InstructorRepository instructorRepository){
        super(modelMapper);
        this.instructorRepository = instructorRepository;
    }
    public InstructorResponseDTO createInstructor(InstructorRequestDTO requestDTO) {
        if (instructorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Instructor with the same email already exists");
        }

        Instructor instructor = new Instructor();
        instructor.setFirstName(requestDTO.getFirstName());
        instructor.setLastName(requestDTO.getLastName());
        instructor.setEmail(requestDTO.getEmail());

        Instructor savedInstructor = instructorRepository.save(instructor);

        return convertInstructorToResponseDTO(savedInstructor);
    }
}
