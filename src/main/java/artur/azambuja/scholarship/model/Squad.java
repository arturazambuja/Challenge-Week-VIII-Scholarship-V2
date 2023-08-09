package artur.azambuja.scholarship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_squad", unique = true, nullable = false)
    private long idSquad;
    @NotBlank(message = "Squad must have a name")
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @NotEmpty(message = "Member cannot be empty")
    @Column(name = "members", nullable = false)
    private int members;
    @ManyToMany(mappedBy = "squads")
    private List<Classroom> classroom;
}
