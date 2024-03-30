package bitlab.to2024g1multipledb.tilidb.repository;

import bitlab.to2024g1multipledb.tilidb.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
