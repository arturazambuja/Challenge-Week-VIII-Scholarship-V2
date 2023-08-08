package artur.azambuja.scholarship.repository;

import artur.azambuja.scholarship.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface coordinatorRepository extends JpaRepository<Coordinator, Long> {
}
