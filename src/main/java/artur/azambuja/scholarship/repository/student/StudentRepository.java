package artur.azambuja.scholarship.repository.student;

import artur.azambuja.scholarship.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClassroom_IdClassroom(Long classroomId);
}
