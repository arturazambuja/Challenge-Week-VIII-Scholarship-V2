package artur.azambuja.scholarship.repository.instructor;

import artur.azambuja.scholarship.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface instructorRepository extends JpaRepository<Instructor, Long> {
}
