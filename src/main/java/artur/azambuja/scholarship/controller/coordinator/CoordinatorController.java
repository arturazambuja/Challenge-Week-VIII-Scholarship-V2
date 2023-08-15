package artur.azambuja.scholarship.controller.coordinator;

import artur.azambuja.scholarship.dto.coordinator.CoordinatorRequestDTO;
import artur.azambuja.scholarship.dto.coordinator.CoordinatorResponseDTO;
import artur.azambuja.scholarship.exceptions.EmailAlreadyRegistredException;
import artur.azambuja.scholarship.exceptions.coordinator.CoordinatorNotFoundException;
import artur.azambuja.scholarship.service.coordinator.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/coordinators")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    @Autowired
    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @GetMapping
    public List<CoordinatorResponseDTO> getAllCoordinators() {
        return coordinatorService.getAllCoordinators();
    }
    @GetMapping("/{idCoordinator}")
    public CoordinatorResponseDTO getCoordinatorById(@PathVariable Long idCoordinator) throws CoordinatorNotFoundException {
        return coordinatorService.getCoordinatorById(idCoordinator);
    }
    @PostMapping
    public CoordinatorResponseDTO createCoordinator(@RequestBody CoordinatorRequestDTO requestDTO) throws EmailAlreadyRegistredException {
        return coordinatorService.createCoordinator(requestDTO);
    }
    @PutMapping("/{idCoordinator}")
    public CoordinatorResponseDTO updateCoordinator(@PathVariable Long idCoordinator, @RequestBody CoordinatorRequestDTO requestDTO) throws CoordinatorNotFoundException {
        return coordinatorService.updateCoordinator(idCoordinator, requestDTO);
    }
    @DeleteMapping("/{idCoordinator}")
    public void deleteCoordinator(@PathVariable Long idCoordinator) throws CoordinatorNotFoundException {
        coordinatorService.deleteCoordinator(idCoordinator);
    }
}
