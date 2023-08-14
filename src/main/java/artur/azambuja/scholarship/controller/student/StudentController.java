package artur.azambuja.scholarship.controller.student;

import artur.azambuja.scholarship.dto.student.StudentRequestDTO;
import artur.azambuja.scholarship.dto.student.StudentResponseDTO;
import artur.azambuja.scholarship.exceptions.classroom.ClassroomFullException;
import artur.azambuja.scholarship.exceptions.classroom.ClassroomNotFoundException;
import artur.azambuja.scholarship.exceptions.student.StudentNotFoundException;
import artur.azambuja.scholarship.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/{idStudent}")
    public StudentResponseDTO getStudentById(@PathVariable Long idStudent) throws StudentNotFoundException {
        return studentService.getStudentById(idStudent);
    }
    @PostMapping
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO requestDTO) throws ClassroomFullException, ClassroomNotFoundException {
        return studentService.createStudent(requestDTO);
    }
    @PutMapping("/{idStudent}")
    public StudentResponseDTO updateStudent(@PathVariable Long idStudent, @RequestBody StudentRequestDTO requestDTO) throws StudentNotFoundException {
        return studentService.updateStudent(idStudent, requestDTO);
    }
    @DeleteMapping("/{idStudent}")
    public void deleteStudent(@PathVariable Long idStudent) throws StudentNotFoundException {
        studentService.deleteStudent(idStudent);
    }
}
