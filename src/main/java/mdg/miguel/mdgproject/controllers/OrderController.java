package mdg.miguel.mdgproject.controllers;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mdg.miguel.mdgproject.dtos.OrderDTO;
import mdg.miguel.mdgproject.dtos.OrderResponseDTO;
import mdg.miguel.mdgproject.dtos.UpdateOrderStatusDTO;
import mdg.miguel.mdgproject.services.OrderService;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/novo-pedido")
  public ResponseEntity<String> newOrder(@RequestBody @Valid OrderDTO dto) {
    orderService.save(dto);
    return ResponseEntity.ok("Pedido cadastrado com sucesso!");
  }

  @GetMapping("/buscar")
  public ResponseEntity<Page<OrderResponseDTO>> getOrders(
      @RequestParam Long resellerId,
      @RequestParam LocalDate firstDate,
      @RequestParam LocalDate lastDate,
      @PageableDefault(size = 20, sort = "date", direction = Sort.Direction.DESC) Pageable pageable) {

    Page<OrderResponseDTO> orders = orderService.getOrdersByReseller(resellerId, firstDate, lastDate, pageable);
    return ResponseEntity.ok(orders);
  }

  @PatchMapping("/atualizar-status")
  public ResponseEntity<String> updateOrderStatus(@RequestBody UpdateOrderStatusDTO dto) {
    orderService.updateOrderStatus(dto);
    return ResponseEntity.ok("Status do pedido atualizado com sucesso!");
  }

}
