package artur.azambuja.scholarship.service.coordinator;

import artur.azambuja.scholarship.dto.coordinator.CoordinatorRequestDTO;
import artur.azambuja.scholarship.dto.coordinator.CoordinatorResponseDTO;
import artur.azambuja.scholarship.model.Coordinator;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorService extends serviceClass {
    public CoordinatorService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public CoordinatorResponseDTO convertCoordinatorToResponseDTO(Coordinator coordinator) {
        return modelMapper.map(coordinator, CoordinatorResponseDTO.class);
    }
    public Coordinator convertCoordinatorRequestDTOToEntity(CoordinatorRequestDTO dto) {
        return modelMapper.map(dto, Coordinator.class);
    }
}