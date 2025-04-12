package mdg.miguel.mdgproject.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mdg.miguel.mdgproject.entities.DailyProduction;

public interface ProductionRepository extends JpaRepository<DailyProduction, Long> {

  boolean existsByDate(LocalDate date);

  Optional<DailyProduction> findByDate(LocalDate date);

}
