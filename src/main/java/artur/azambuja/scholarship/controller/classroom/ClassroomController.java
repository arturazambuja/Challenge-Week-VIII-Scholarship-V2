package artur.azambuja.scholarship.controller.classroom;

import artur.azambuja.scholarship.dto.classroom.ClassroomRequestDTO;
import artur.azambuja.scholarship.dto.classroom.ClassroomResponseDTO;
import artur.azambuja.scholarship.exceptions.Instructor.InsufficientInstructorsException;
import artur.azambuja.scholarship.exceptions.classroom.ClassroomNotFoundException;
import artur.azambuja.scholarship.exceptions.student.InsufficientStudentsException;
import artur.azambuja.scholarship.service.classroom.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<ClassroomResponseDTO> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }
    @GetMapping("/{idClassroom}")
    public ClassroomResponseDTO getClassroomById(@PathVariable Long idClassroom) throws ClassroomNotFoundException {
        return classroomService.getClassroomById(idClassroom);
    }
    @PostMapping
    public ResponseEntity<ClassroomResponseDTO> createClassroom(@RequestBody ClassroomRequestDTO requestDTO) throws InsufficientInstructorsException, InsufficientStudentsException {
        return classroomService.createClassroom(requestDTO);
    }
    @PostMapping("/{idClassroom}/start")
    public ResponseEntity<String> startClassroom(@PathVariable Long idClassroom) {
        try {
            classroomService.startClassroom(idClassroom);
            return ResponseEntity.ok("Classroom started successfully.");
        } catch (ClassroomNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{idClassroom}/finish")
    public ResponseEntity<String> finishClassroom(@PathVariable Long idClassroom) {
        try {
            classroomService.finishClassroom(idClassroom);
            return ResponseEntity.ok("Classroom finished successfully.");
        } catch (ClassroomNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{idClassroom}")
    public ClassroomResponseDTO updateClassroom(@PathVariable Long idClassroom, @RequestBody ClassroomRequestDTO requestDTO) throws ClassroomNotFoundException {
        return classroomService.updateClassroom(idClassroom, requestDTO);
    }
}
