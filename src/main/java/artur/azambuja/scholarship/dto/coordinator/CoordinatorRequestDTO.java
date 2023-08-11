package artur.azambuja.scholarship.dto.coordinator;

import artur.azambuja.scholarship.model.Classroom;
import lombok.Data;

import java.util.List;
@Data
public class CoordinatorRequestDTO {
    private long idCoordinator;
    private String firstName;
    private String lastName;
    private String email;
    private List<Classroom> classroom;
}
