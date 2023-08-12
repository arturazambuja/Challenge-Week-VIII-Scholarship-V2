package artur.azambuja.scholarship.repository.scrumMaster;

import artur.azambuja.scholarship.model.ScrumMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long> {
    @Query("SELECT s.idScrumMaster FROM ScrumMaster s")
    ScrumMaster findAnyScrumMaster();

    boolean existsByEmail(String email);
}
