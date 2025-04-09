package mdg.miguel.mdgproject.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mdg.miguel.mdgproject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByResellerIdAndDateBetween(Long resellerId, LocalDate firstDate, LocalDate lastDate);

  Optional<Order> findByOrderCode(String orderCode);

}
