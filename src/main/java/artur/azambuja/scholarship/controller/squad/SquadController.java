package artur.azambuja.scholarship.controller.squad;

import artur.azambuja.scholarship.dto.squad.SquadRequestDTO;
import artur.azambuja.scholarship.dto.squad.SquadResponseDTO;
import artur.azambuja.scholarship.exceptions.squad.SquadNotFoundException;
import artur.azambuja.scholarship.service.squad.SquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/squads")
public class SquadController {

    private final SquadService squadService;

    @Autowired
    public SquadController(SquadService squadService) {
        this.squadService = squadService;
    }

    @GetMapping
    public List<SquadResponseDTO> getAllSquads() {
        return squadService.getAllSquads();
    }
    @GetMapping("/{idSquad}")
    public SquadResponseDTO getSquadById(@PathVariable Long idSquad) throws SquadNotFoundException {
        return squadService.getSquadById(idSquad);
    }
    @PutMapping("/{idSquad}")
    public SquadResponseDTO updateSquad(@PathVariable Long idSquad, @RequestBody SquadRequestDTO requestDTO) throws SquadNotFoundException {
        return squadService.updateSquad(idSquad, requestDTO);
    }
    @DeleteMapping("/{idSquad}")
    public void deleteSquad(@PathVariable Long idSquad) throws SquadNotFoundException {
        squadService.deleteSquad(idSquad);
    }
}
