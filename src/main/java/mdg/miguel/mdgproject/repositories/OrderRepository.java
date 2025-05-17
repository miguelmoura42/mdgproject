package mdg.miguel.mdgproject.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mdg.miguel.mdgproject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Page<Order> findByResellerIdAndDateBetween(Long resellerId, LocalDate firstDate, LocalDate lastDate,
      Pageable pageable);

  Optional<Order> findByOrderCode(String orderCode);

}
