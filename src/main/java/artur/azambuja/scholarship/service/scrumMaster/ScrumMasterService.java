package artur.azambuja.scholarship.service.scrumMaster;

import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterRequestDTO;
import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterResponseDTO;
import artur.azambuja.scholarship.model.ScrumMaster;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ScrumMasterService extends serviceClass {
    public ScrumMasterService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public ScrumMasterResponseDTO convertScrumMasterToResponseDTO(ScrumMaster scrumMaster) {
        return modelMapper.map(scrumMaster, ScrumMasterResponseDTO.class);
    }
    public ScrumMaster convertScrumMasterRequestDTOToEntity(ScrumMasterRequestDTO dto) {
        return modelMapper.map(dto, ScrumMaster.class);
    }
}
