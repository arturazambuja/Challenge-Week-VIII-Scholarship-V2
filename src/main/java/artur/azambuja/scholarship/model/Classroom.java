package artur.azambuja.scholarship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Entity
@Data
@Table(name = "classroom")
public class Classroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classroom", unique = true, nullable = false)
    private long idClassroom;
    @NotBlank(message = "Room cannot be empty")
    @Column(name = "classroom", unique = true, nullable = false)
    private String classroom;
    @NotBlank(message = "The class must have at least one Coordinator")
    @Column(name = "coordinator", unique = true, nullable = false)
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
    @OneToMany(mappedBy = "classroom")
    private List<Student> students;
    @ManyToMany
    @JoinTable(
            name = "classroom_coordinator",
            joinColumns = @JoinColumn(name = "id_classroom"),
            inverseJoinColumns = @JoinColumn(name = "id_coordinator")
    )
    private List<Coordinator> coordinators;
    @ManyToMany
    @JoinTable(
            name = "classroom_instructor",
            joinColumns = @JoinColumn(name = "id_classroom"),
            inverseJoinColumns = @JoinColumn(name = "id_instructor")
    )
    private List<Instructor> instructors;
    @ManyToMany
    @JoinTable(
            name = "classroom_scrum_master",
            joinColumns = @JoinColumn(name = "id_classroom"),
            inverseJoinColumns = @JoinColumn(name = "id_scrum_master")
    )
    private List<ScrumMaster> scrumMasters;
    @ManyToMany
    @JoinTable(
            name = "classroom_squad",
            joinColumns = @JoinColumn(name = "id_classroom"),
            inverseJoinColumns = @JoinColumn(name = "id_squad")
    )
    private List<Squad> squads;

    public void setCoordinator(Coordinator coordinator) {
    }
    public void setScrumMaster(ScrumMaster scrumMaster) {
    }
}
