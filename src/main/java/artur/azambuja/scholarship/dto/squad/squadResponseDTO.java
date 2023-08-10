package artur.azambuja.scholarship.dto.squad;

import artur.azambuja.scholarship.model.Classroom;
import lombok.Data;

import java.util.List;
@Data
public class squadResponseDTO {
    private String name;
    private int members;
    private List<Classroom> classroom;

}
