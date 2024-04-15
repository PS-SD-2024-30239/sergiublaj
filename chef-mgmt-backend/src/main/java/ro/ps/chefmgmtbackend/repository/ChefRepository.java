package ro.ps.chefmgmtbackend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ps.chefmgmtbackend.model.ChefEntity;

@Repository
public interface ChefRepository extends JpaRepository<ChefEntity, UUID> {

    List<ChefEntity> findAllByRatingGreaterThan(double rating);
}
