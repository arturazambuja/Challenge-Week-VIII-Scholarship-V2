package artur.azambuja.scholarship.repository.student;

import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Squad;
import artur.azambuja.scholarship.model.Student;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClassroom_IdClassroom(Long classroomId);
    List<Student> findBySquad(Squad squad);
    @Query("SELECT s FROM Student s WHERE s.classroom IS NULL")
    List<Student> findAvailableStudentsForClassroom(Classroom classroom);
}
