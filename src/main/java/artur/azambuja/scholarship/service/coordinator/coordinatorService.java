package artur.azambuja.scholarship.service.coordinator;

import artur.azambuja.scholarship.dto.coordinator.coordinatorRequestDTO;
import artur.azambuja.scholarship.dto.coordinator.coordinatorResponseDTO;
import artur.azambuja.scholarship.model.Coordinator;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class coordinatorService extends serviceClass {
    public coordinatorService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public coordinatorResponseDTO convertCoordinatorToResponseDTO(Coordinator coordinator) {
        return modelMapper.map(coordinator, coordinatorResponseDTO.class);
    }
    public Coordinator convertCoordinatorRequestDTOToEntity(coordinatorRequestDTO dto) {
        return modelMapper.map(dto, Coordinator.class);
    }
}