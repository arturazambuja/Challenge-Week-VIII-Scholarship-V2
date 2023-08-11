package artur.azambuja.scholarship.repository.instructor;

import artur.azambuja.scholarship.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("SELECT i.idInstructor FROM Instructor i")
    List<Instructor> findAnyInstructors(int count);
}
