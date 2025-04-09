package mdg.miguel.mdgproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mdg.miguel.mdgproject.entities.Reseller;
import mdg.miguel.mdgproject.enums.Cities;

public interface ResellerRepository extends JpaRepository<Reseller, Long> {
  boolean existsByName(String name);

  boolean existsByUniqueKey(String uniqueKey);

  List<Reseller> findByNameContainingIgnoreCase(String name);

  List<Reseller> findByCity(Cities city);

  Optional<Reseller> findByUniqueKey(String uniqueKey);

}