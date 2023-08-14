package artur.azambuja.scholarship.repository.classroom;

import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Coordinator;
import artur.azambuja.scholarship.model.Instructor;
import artur.azambuja.scholarship.model.ScrumMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByCoordinators(Coordinator coordinator);
    List<Classroom> findByInstructors(Instructor instructor);
    List<Classroom> findByScrumMasters(ScrumMaster scrumMaster);
}
