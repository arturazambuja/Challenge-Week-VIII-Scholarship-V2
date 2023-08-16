package artur.azambuja.scholarship.dto.squad;

import artur.azambuja.scholarship.model.Classroom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquadResponseDTO {
    private long idSquad;
    private String name;
    @JsonIgnore
    private int number;
    @JsonIgnore
    private List<Classroom> classroom;

}
