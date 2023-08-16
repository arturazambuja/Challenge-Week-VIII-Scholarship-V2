package artur.azambuja.scholarship.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student", unique = true, nullable = false)
    private long idStudent;
    @NotBlank(message = "first name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotBlank(message = "last name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Email(message = "email cannot be empty")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_classroom")
    @JsonBackReference
    private Classroom classroom;
    @ManyToOne
    private Squad squad;
}
