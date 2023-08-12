package artur.azambuja.scholarship.repository.coordinator;

import artur.azambuja.scholarship.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
    @Query("SELECT c.idCoordinator FROM Coordinator c")
    Coordinator findAnyCoordinator();

    boolean existsByEmail(String email);
}
