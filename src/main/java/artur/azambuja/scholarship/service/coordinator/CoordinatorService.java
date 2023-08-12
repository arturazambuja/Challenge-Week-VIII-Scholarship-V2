package artur.azambuja.scholarship.service.coordinator;

import artur.azambuja.scholarship.dto.coordinator.CoordinatorRequestDTO;
import artur.azambuja.scholarship.dto.coordinator.CoordinatorResponseDTO;
import artur.azambuja.scholarship.model.Coordinator;
import artur.azambuja.scholarship.repository.coordinator.CoordinatorRepository;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorService extends serviceClass {
    public CoordinatorResponseDTO convertCoordinatorToResponseDTO(Coordinator coordinator) {
        return modelMapper.map(coordinator, CoordinatorResponseDTO.class);
    }
    public Coordinator convertCoordinatorRequestDTOToEntity(CoordinatorRequestDTO dto) {
        return modelMapper.map(dto, Coordinator.class);
    }
    private final CoordinatorRepository coordinatorRepository;
    public CoordinatorService(ModelMapper modelMapper, CoordinatorRepository coordinatorRepository){
        super(modelMapper);
        this.coordinatorRepository = coordinatorRepository;
    }
    public CoordinatorResponseDTO createInstructor(CoordinatorRequestDTO requestDTO) {
        if (coordinatorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Coordinator with the same email already exists");
        }

        Coordinator coordinator = new Coordinator();
        coordinator.setFirstName(requestDTO.getFirstName());
        coordinator.setLastName(requestDTO.getLastName());
        coordinator.setEmail(requestDTO.getEmail());

        Coordinator savedCoordinator = coordinatorRepository.save(coordinator);

        return convertCoordinatorToResponseDTO(savedCoordinator);
    }
}