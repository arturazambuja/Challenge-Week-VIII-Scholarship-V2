package artur.azambuja.scholarship.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_squad", unique = true, nullable = false)
    private long idSquad;
    @NotBlank(message = "Squad must have a name")
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @NotEmpty(message = "number cannot be empty")
    @Column(name = "number", nullable = false)
    private int number;
    @ManyToOne
    private Classroom classroom;
    @OneToMany(mappedBy = "squad")
    @JsonBackReference
    private List<Student> students;
}
