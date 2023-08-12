package artur.azambuja.scholarship.service.coordinator;

import artur.azambuja.scholarship.dto.coordinator.CoordinatorRequestDTO;
import artur.azambuja.scholarship.dto.coordinator.CoordinatorResponseDTO;
import artur.azambuja.scholarship.exceptions.EmailAlreadyRegistredException;
import artur.azambuja.scholarship.exceptions.coordinator.CoordinatorNotFoundException;
import artur.azambuja.scholarship.model.Coordinator;
import artur.azambuja.scholarship.repository.coordinator.CoordinatorRepository;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public CoordinatorResponseDTO createInstructor(CoordinatorRequestDTO requestDTO) throws EmailAlreadyRegistredException {
        if (coordinatorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailAlreadyRegistredException("Coordinator with the same email already exists");
        }

        Coordinator coordinator = new Coordinator();
        coordinator.setFirstName(requestDTO.getFirstName());
        coordinator.setLastName(requestDTO.getLastName());
        coordinator.setEmail(requestDTO.getEmail());

        Coordinator savedCoordinator = coordinatorRepository.save(coordinator);

        return convertCoordinatorToResponseDTO(savedCoordinator);
    }
    public List<CoordinatorResponseDTO> getAllCoordinators() {
        List<Coordinator> coordinators = coordinatorRepository.findAll();
        return coordinators.stream()
                .map(this::convertCoordinatorToResponseDTO)
                .collect(Collectors.toList());
    }

    public CoordinatorResponseDTO getCoordinatorById(Long coordinatorId) throws CoordinatorNotFoundException {
        Coordinator coordinator = coordinatorRepository.findById(coordinatorId)
                .orElseThrow(() -> new CoordinatorNotFoundException("Coordinator not found with this id"));
        return convertCoordinatorToResponseDTO(coordinator);
    }

    public CoordinatorResponseDTO updateCoordinator(Long coordinatorId, CoordinatorRequestDTO requestDTO) throws CoordinatorNotFoundException {
        Coordinator coordinator = coordinatorRepository.findById(coordinatorId)
                .orElseThrow(() -> new CoordinatorNotFoundException("Coordinator not found with this id"));

        coordinator.setFirstName(requestDTO.getFirstName());
        coordinator.setLastName(requestDTO.getLastName());
        coordinator.setEmail(requestDTO.getEmail());

        coordinatorRepository.save(coordinator);
        return convertCoordinatorToResponseDTO(coordinator);
    }
}