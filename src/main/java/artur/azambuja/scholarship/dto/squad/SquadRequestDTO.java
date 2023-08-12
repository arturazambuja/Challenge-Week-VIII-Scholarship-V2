package artur.azambuja.scholarship.dto.squad;

import artur.azambuja.scholarship.model.Classroom;
import lombok.Data;

import java.util.List;
@Data
public class SquadRequestDTO {
    private long idSquad;
    private String name;
    private int number;
    private List<Classroom> classroom;
}
