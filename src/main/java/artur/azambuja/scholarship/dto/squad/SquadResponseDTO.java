package artur.azambuja.scholarship.dto.squad;

import artur.azambuja.scholarship.model.Classroom;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquadResponseDTO {
    private long idSquad;
    private String name;
    private int members;
    private List<Classroom> classroom;

}
