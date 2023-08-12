package artur.azambuja.scholarship.dto.instructor;

import artur.azambuja.scholarship.model.Classroom;
import lombok.Data;

import java.util.List;
@Data
public class InstructorRequestDTO {
    private long idInstructor;
    private String firstName;
    private String lastName;
    private String email;
    private List<Classroom> classroom;
}
