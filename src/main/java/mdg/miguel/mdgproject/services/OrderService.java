package mdg.miguel.mdgproject.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import mdg.miguel.mdgproject.dtos.OrderDTO;
import mdg.miguel.mdgproject.dtos.OrderResponseDTO;
import mdg.miguel.mdgproject.dtos.UpdateOrderStatusDTO;
import mdg.miguel.mdgproject.entities.Order;
import mdg.miguel.mdgproject.entities.Reseller;
import mdg.miguel.mdgproject.exceptions.OrderNotFoundException;
import mdg.miguel.mdgproject.repositories.OrderRepository;
import mdg.miguel.mdgproject.services.validators.OrderValidator;

@Service
public class OrderService {

  private OrderRepository orderRepository;
  private OrderValidator orderValidator;
  private ResellerService resellerService;

  public OrderService(OrderRepository orderRepository, OrderValidator orderValidator, ResellerService resellerService) {
    this.orderRepository = orderRepository;
    this.orderValidator = orderValidator;
    this.resellerService = resellerService;
  }

  public void save(OrderDTO dto) {
    orderValidator.validate(dto);
    var reseller = resellerService.getAndValidateReseller(dto.getUniqueKey());

    var order = new Order();
    BeanUtils.copyProperties(dto, order);
    order.setReseller(reseller);
    String orderCode = generateOrderCode();
    order.setOrderCode(orderCode);

    orderRepository.save(order);
  }

  public List<OrderResponseDTO> getOrdersByReseller(Long resellerId, LocalDate firstDate, LocalDate lastDate) {
    if (firstDate.isAfter(lastDate)) {
      throw new IllegalArgumentException("Data inicial não pode ser após a data final.");
    }

    List<Order> orders = orderRepository.findByResellerIdAndDateBetween(resellerId, firstDate, lastDate);
    if (orders.isEmpty()) {
      throw new OrderNotFoundException("Não há pedidos para o id informado!");
    }
    return orders.stream()
        .map(order -> new OrderResponseDTO(order.getQuantity(), order.getAmount(), order.getDate(), order.getStatus(),
            order.getOrderCode()))
        .toList();
  }

  public void updateOrderStatus(UpdateOrderStatusDTO dto) {
    Order order = orderRepository.findByOrderCode(dto.getOrderCode())
        .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado com o código: " + dto.getOrderCode()));

    order.setStatus(dto.getNewStatus());
  }

  private String generateOrderCode() {
    String prefix = "PED";
    String datePart = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    String uniquePart = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    return String.format("%s-%s-%s", prefix, datePart, uniquePart);
  }

}
