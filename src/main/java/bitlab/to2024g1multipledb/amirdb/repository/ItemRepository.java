package bitlab.to2024g1multipledb.amirdb.repository;

import bitlab.to2024g1multipledb.amirdb.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
