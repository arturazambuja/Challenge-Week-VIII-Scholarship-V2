package artur.azambuja.scholarship.service.instructor;

import artur.azambuja.scholarship.dto.instructor.InstructorRequestDTO;
import artur.azambuja.scholarship.dto.instructor.InstructorResponseDTO;
import artur.azambuja.scholarship.exceptions.EmailAlreadyRegistredException;
import artur.azambuja.scholarship.exceptions.Instructor.InstructorNotFoundException;
import artur.azambuja.scholarship.model.Instructor;
import artur.azambuja.scholarship.repository.instructor.InstructorRepository;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public InstructorResponseDTO createInstructor(InstructorRequestDTO requestDTO) throws EmailAlreadyRegistredException {
        if (instructorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailAlreadyRegistredException("Instructor with the same email already exists");
        }

        Instructor instructor = new Instructor();
        instructor.setFirstName(requestDTO.getFirstName());
        instructor.setLastName(requestDTO.getLastName());
        instructor.setEmail(requestDTO.getEmail());

        Instructor savedInstructor = instructorRepository.save(instructor);

        return convertInstructorToResponseDTO(savedInstructor);
    }
    public List<InstructorResponseDTO> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream()
                .map(this::convertInstructorToResponseDTO)
                .collect(Collectors.toList());
    }

    public InstructorResponseDTO getInstructorById(Long instructorId) throws InstructorNotFoundException {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with this id"));
        return convertInstructorToResponseDTO(instructor);
    }

    public InstructorResponseDTO updateInstructor(Long instructorId, InstructorRequestDTO requestDTO) throws InstructorNotFoundException {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with this id"));

        instructor.setFirstName(requestDTO.getFirstName());
        instructor.setLastName(requestDTO.getLastName());
        instructor.setEmail(requestDTO.getEmail());

        instructorRepository.save(instructor);
        return convertInstructorToResponseDTO(instructor);
    }
}
