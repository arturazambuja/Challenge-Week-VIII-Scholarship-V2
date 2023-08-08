package artur.azambuja.scholarship.repository;


import artur.azambuja.scholarship.model.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface squadRepository extends JpaRepository<Squad, Long> {
}
