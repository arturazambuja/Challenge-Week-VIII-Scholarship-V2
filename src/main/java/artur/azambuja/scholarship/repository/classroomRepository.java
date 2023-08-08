package artur.azambuja.scholarship.repository;

import artur.azambuja.scholarship.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface classroomRepository extends JpaRepository<Classroom, Long> {
}
