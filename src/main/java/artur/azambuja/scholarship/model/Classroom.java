package artur.azambuja.scholarship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "Rooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classroom", unique = true, nullable = false)
    private long idClassroom;
    @NotBlank(message = "Room cannot be empty")
    @Column(name = "classroom", unique = true, nullable = false)
    private String classroom;
    @NotBlank(message = "The class must have at least one coordinator ")
    @Column(name = "coordinator", nullable = false)
    private String coordinator;
    @NotBlank(message = "The class must have at least one Scrum Master")
    @Column(name = "scrum_master", unique = true, nullable = false)
    private String scrumMaster;
    @NotBlank(message = "the class must have at least three instructors")
    @Column(name = "instructor", nullable = false)
    private String instructor;
    @NotBlank(message = "Status cannot be empty")
    @Column(name = "status", nullable = false)
    private String status;
}
