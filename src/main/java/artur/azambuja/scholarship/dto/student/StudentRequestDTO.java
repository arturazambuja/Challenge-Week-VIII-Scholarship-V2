package artur.azambuja.scholarship.dto.student;

import artur.azambuja.scholarship.model.Classroom;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Classroom classroom;

}
