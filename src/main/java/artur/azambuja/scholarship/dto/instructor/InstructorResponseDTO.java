package artur.azambuja.scholarship.dto.instructor;

import artur.azambuja.scholarship.model.Classroom;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorResponseDTO {
    private long idInstructor;
    private String firstName;
    private String lastName;
    private String email;
    private List<Classroom> classroom;
}
