package artur.azambuja.scholarship.dto.classroom;

import artur.azambuja.scholarship.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomRequestDTO {
    private String classroom;
    private String coordinator;
    private String scrumMaster;
    private String instructor;
    private String status;
    private List<Student> students;
    private List<Coordinator> coordinators;
    private List<Instructor> instructors;
    private List<ScrumMaster> scrumMasters;
    private List<Squad> squads;
}
