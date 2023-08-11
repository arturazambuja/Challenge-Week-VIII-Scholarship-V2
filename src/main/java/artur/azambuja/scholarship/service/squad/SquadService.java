package artur.azambuja.scholarship.service.squad;

import artur.azambuja.scholarship.dto.squad.SquadRequestDTO;
import artur.azambuja.scholarship.dto.squad.SquadResponseDTO;
import artur.azambuja.scholarship.model.Squad;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SquadService extends serviceClass {
    public SquadService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public SquadResponseDTO convertSquadToResponseDTO(Squad squad) {
        return modelMapper.map(squad, SquadResponseDTO.class);
    }
    public Squad convertSquadRequestDTOToEntity(SquadRequestDTO dto) {
        return modelMapper.map(dto, Squad.class);
    }
}
