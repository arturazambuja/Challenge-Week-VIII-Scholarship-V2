package artur.azambuja.scholarship.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "number", nullable = false)
    private int number;
    @ManyToOne
    @JsonBackReference
    private Classroom classroom;
    @OneToMany
    private List<Student> students;
}
