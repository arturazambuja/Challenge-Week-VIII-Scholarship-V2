package artur.azambuja.scholarship.service.squad;

import artur.azambuja.scholarship.dto.squad.squadRequestDTO;
import artur.azambuja.scholarship.dto.squad.squadResponseDTO;
import artur.azambuja.scholarship.model.Squad;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class squadService extends serviceClass {
    public squadService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public squadResponseDTO convertSquadToResponseDTO(Squad squad) {
        return modelMapper.map(squad, squadResponseDTO.class);
    }
    public Squad convertSquadRequestDTOToEntity(squadRequestDTO dto) {
        return modelMapper.map(dto, Squad.class);
    }
}
