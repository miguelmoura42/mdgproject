package mdg.miguel.mdgproject.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mdg.miguel.mdgproject.entities.Reseller;
import mdg.miguel.mdgproject.enums.Cities;

public interface ResellerRepository extends JpaRepository<Reseller, Long> {
  boolean existsByName(String name);

  boolean existsByUniqueKey(String uniqueKey);

  Page<Reseller> findByCity(Cities city, Pageable pageable);

  Page<Reseller> findByNameContainingIgnoreCase(String name, Pageable pageable);

  Optional<Reseller> findByUniqueKey(String uniqueKey);

}