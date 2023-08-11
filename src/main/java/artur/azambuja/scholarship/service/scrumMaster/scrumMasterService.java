package artur.azambuja.scholarship.service.scrumMaster;

import artur.azambuja.scholarship.dto.scrumMaster.scrumMasterRequestDTO;
import artur.azambuja.scholarship.dto.scrumMaster.scrumMasterResponseDTO;
import artur.azambuja.scholarship.model.ScrumMaster;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class scrumMasterService extends serviceClass {
    public scrumMasterService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public scrumMasterResponseDTO convertScrumMasterToResponseDTO(ScrumMaster scrumMaster) {
        return modelMapper.map(scrumMaster, scrumMasterResponseDTO.class);
    }
    public ScrumMaster convertScrumMasterRequestDTOToEntity(scrumMasterRequestDTO dto) {
        return modelMapper.map(dto, ScrumMaster.class);
    }
}
