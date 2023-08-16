package artur.azambuja.scholarship.model;

import artur.azambuja.scholarship.dto.classroom.ClassroomResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "classroom")
public class Classroom extends ClassroomResponseDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classroom", unique = true, nullable = false)
    private long idClassroom;
    @Column(name = "classroom")
    private String classroom;
    @NotBlank(message = "Status cannot be empty")
    @Column(name = "status", nullable = false)
    private String status;
    @OneToMany
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
}
