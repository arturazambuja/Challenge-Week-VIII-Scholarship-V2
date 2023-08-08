package artur.azambuja.scholarship.repository;

import artur.azambuja.scholarship.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<Student, Long> {
}
