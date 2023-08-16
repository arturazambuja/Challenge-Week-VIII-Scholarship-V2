package artur.azambuja.scholarship.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Scrum_Masters")
public class ScrumMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scrum_master", unique = true, nullable = false)
    private long idScrumMaster;
    @NotBlank(message = "first name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotBlank(message = "last name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Email(message = "email cannot be empty")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @ManyToMany(mappedBy = "scrumMasters")
    @JsonBackReference
    private List<Classroom> classroom;
}
