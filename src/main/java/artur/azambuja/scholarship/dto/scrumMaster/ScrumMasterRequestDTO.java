package artur.azambuja.scholarship.dto.scrumMaster;

import artur.azambuja.scholarship.model.Classroom;
import lombok.Data;

import java.util.List;
@Data
public class ScrumMasterRequestDTO {
    private long idScrumMaster;
    private String firstName;
    private String lastName;
    private String email;
    private List<Classroom> classroom;
}
