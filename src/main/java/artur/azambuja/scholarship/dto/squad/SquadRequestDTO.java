package artur.azambuja.scholarship.dto.squad;

import artur.azambuja.scholarship.model.Classroom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquadRequestDTO {
    private String name;
    private int number;
    private List<Classroom> classroom;
}
