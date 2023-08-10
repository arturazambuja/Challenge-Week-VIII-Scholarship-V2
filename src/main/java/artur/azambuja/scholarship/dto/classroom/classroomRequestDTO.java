package artur.azambuja.scholarship.dto.classroom;

import artur.azambuja.scholarship.model.*;
import lombok.Data;

import java.util.List;
@Data
public class classroomRequestDTO {
    private long idClassroom;
    private String classroom;
    private String scrumMaster;
    private String instructor;
    private String status;
    private List<Student> students;
    private List<Coordinator> coordinators;
    private List<Instructor> instructors;
    private List<ScrumMaster> scrumMasters;
    private List<Squad> squads;
}
