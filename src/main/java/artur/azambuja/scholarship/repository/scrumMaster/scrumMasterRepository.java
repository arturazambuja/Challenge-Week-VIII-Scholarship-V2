package artur.azambuja.scholarship.repository.scrumMaster;

import artur.azambuja.scholarship.model.ScrumMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface scrumMasterRepository extends JpaRepository<ScrumMaster, Long> {
}
