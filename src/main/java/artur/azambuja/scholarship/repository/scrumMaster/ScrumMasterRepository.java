package artur.azambuja.scholarship.repository.scrumMaster;

import artur.azambuja.scholarship.model.ScrumMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long> {
    @Query("SELECT s FROM ScrumMaster s")
    List<ScrumMaster> findAnyScrumMaster();

    boolean existsByEmail(String email);
}
