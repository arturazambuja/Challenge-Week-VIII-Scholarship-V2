package artur.azambuja.scholarship.controller.instructor;

import artur.azambuja.scholarship.dto.instructor.InstructorRequestDTO;
import artur.azambuja.scholarship.dto.instructor.InstructorResponseDTO;
import artur.azambuja.scholarship.exceptions.EmailAlreadyRegistredException;
import artur.azambuja.scholarship.exceptions.Instructor.InstructorNotFoundException;
import artur.azambuja.scholarship.service.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<InstructorResponseDTO> getAllInstructors() {
        return instructorService.getAllInstructors();
    }
    @GetMapping("/{idInstructor}")
    public InstructorResponseDTO getInstructorById(@PathVariable Long idInstructor) throws InstructorNotFoundException {
        return instructorService.getInstructorById(idInstructor);
    }
    @PostMapping
    public InstructorResponseDTO createInstructor(@RequestBody InstructorRequestDTO requestDTO) throws EmailAlreadyRegistredException {
        return instructorService.createInstructor(requestDTO);
    }
    @PutMapping("/{idInstructor}")
    public InstructorResponseDTO updateInstructor(@PathVariable Long idInstructor, @RequestBody InstructorRequestDTO requestDTO) throws InstructorNotFoundException {
        return instructorService.updateInstructor(idInstructor, requestDTO);
    }
    @DeleteMapping("/{idInstructor}")
    public void deleteInstructor(@PathVariable Long idInstructor) throws InstructorNotFoundException {
        instructorService.deleteInstructor(idInstructor);
    }
}
