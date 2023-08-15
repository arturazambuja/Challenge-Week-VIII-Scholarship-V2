package artur.azambuja.scholarship.dto.scrumMaster;

import artur.azambuja.scholarship.model.Classroom;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScrumMasterResponseDTO {
    private long idScrumMaster;
    private String firstName;
    private String lastName;
    private String email;
    private List<Classroom> classroom;
}
