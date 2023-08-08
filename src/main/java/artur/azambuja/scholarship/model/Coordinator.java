package artur.azambuja.scholarship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "Coordinators")
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coordinator", unique = true, nullable = false)
    private long idCoordinator;
    @NotBlank(message = "first name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotBlank(message = "last name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Email(message = "email cannot be empty")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
}
