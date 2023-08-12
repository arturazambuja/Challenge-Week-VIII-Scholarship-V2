package artur.azambuja.scholarship.service.scrumMaster;

import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterRequestDTO;
import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterResponseDTO;
import artur.azambuja.scholarship.model.ScrumMaster;
import artur.azambuja.scholarship.repository.scrumMaster.ScrumMasterRepository;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ScrumMasterService extends serviceClass {
    public ScrumMasterResponseDTO convertScrumMasterToResponseDTO(ScrumMaster scrumMaster) {
        return modelMapper.map(scrumMaster, ScrumMasterResponseDTO.class);
    }
    public ScrumMaster convertScrumMasterRequestDTOToEntity(ScrumMasterRequestDTO dto) {
        return modelMapper.map(dto, ScrumMaster.class);
    }
    private final ScrumMasterRepository scrumMasterRepository;
    public ScrumMasterService(ModelMapper modelMapper, ScrumMasterRepository scrumMasterRepository){
        super(modelMapper);
        this.scrumMasterRepository = scrumMasterRepository;
    }
    public ScrumMasterResponseDTO createScrumMaster(ScrumMasterRequestDTO requestDTO) {
        if (scrumMasterRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Instructor with the same email already exists");
        }

        ScrumMaster scrumMaster = new ScrumMaster();
        scrumMaster.setFirstName(requestDTO.getFirstName());
        scrumMaster.setLastName(requestDTO.getLastName());
        scrumMaster.setEmail(requestDTO.getEmail());

        ScrumMaster savedInstructor = scrumMasterRepository.save(scrumMaster);

        return convertScrumMasterToResponseDTO(savedInstructor);
    }
}
