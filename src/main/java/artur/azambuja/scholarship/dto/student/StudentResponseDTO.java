package artur.azambuja.scholarship.dto.student;

import artur.azambuja.scholarship.model.Classroom;
import lombok.Data;

@Data
public class StudentResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Classroom classroom;
}