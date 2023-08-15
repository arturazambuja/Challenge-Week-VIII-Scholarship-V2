package artur.azambuja.scholarship.dto.coordinator;

import artur.azambuja.scholarship.model.Classroom;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatorResponseDTO {
    private long idCoordinator;
    private String firstName;
    private String lastName;
    private String email;
    private List<Classroom> classroom;

}
