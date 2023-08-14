package artur.azambuja.scholarship.controller.scrumMaster;

import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterRequestDTO;
import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterResponseDTO;
import artur.azambuja.scholarship.exceptions.EmailAlreadyRegistredException;
import artur.azambuja.scholarship.exceptions.scrumMaster.ScrumMasterNotFoundException;
import artur.azambuja.scholarship.service.scrumMaster.ScrumMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/scrum-masters")
public class ScrumMasterController {

    private final ScrumMasterService scrumMasterService;

    @Autowired
    public ScrumMasterController(ScrumMasterService scrumMasterService) {
        this.scrumMasterService = scrumMasterService;
    }
    @GetMapping
    public List<ScrumMasterResponseDTO> getAllScrumMasters() {
        return scrumMasterService.getAllScrumMasters();
    }
    @GetMapping("/{idSrcumMaster}")
    public ScrumMasterResponseDTO getScrumMasterById(@PathVariable Long idSrcumMaster) throws ScrumMasterNotFoundException {
        return scrumMasterService.getScrumMasterById(idSrcumMaster);
    }
    @PostMapping
    public ScrumMasterResponseDTO createScrumMaster(@RequestBody ScrumMasterRequestDTO requestDTO) throws EmailAlreadyRegistredException {
        return scrumMasterService.createScrumMaster(requestDTO);
    }
    @PutMapping("/{idSrcumMaster}")
    public ScrumMasterResponseDTO updateScrumMaster(@PathVariable Long idSrcumMaster, @RequestBody ScrumMasterRequestDTO requestDTO) throws ScrumMasterNotFoundException {
        return scrumMasterService.updateScrumMaster(idSrcumMaster, requestDTO);
    }
    @DeleteMapping("/{idSrcumMaster}")
    public void deleteScrumMaster(@PathVariable Long idSrcumMaster) throws ScrumMasterNotFoundException {
        scrumMasterService.deleteScrumMaster(idSrcumMaster);
    }
}