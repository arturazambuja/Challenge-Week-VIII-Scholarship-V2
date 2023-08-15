package artur.azambuja.scholarship.dto.student;

import artur.azambuja.scholarship.model.Classroom;
import lombok.Data;

@Data
public class StudentRequestDTO {
    private long idStudent;
    private String firstName;
    private String lastName;
    private String email;
    private Classroom classroom;

}
