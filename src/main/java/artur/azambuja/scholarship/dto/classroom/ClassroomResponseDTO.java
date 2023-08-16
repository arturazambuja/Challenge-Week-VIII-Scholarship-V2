package artur.azambuja.scholarship.dto.classroom;

import artur.azambuja.scholarship.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomResponseDTO {
    private long idClassroom;
    private String classroom;
    private String status;
    private List<Student> students;
    private List<Coordinator> coordinators;
    private List<Instructor> instructors;
    private List<ScrumMaster> scrumMasters;
    @JsonIgnore
    private List<Squad> squads;
}
