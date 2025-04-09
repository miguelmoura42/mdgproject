package mdg.miguel.mdgproject.controllers;

import java.time.LocalDate;
import java.util.List;

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
  public ResponseEntity<List<OrderResponseDTO>> getOrders(@RequestParam Long resellerId,
      @RequestParam LocalDate firstDate, @RequestParam LocalDate lastDate) {
    List<OrderResponseDTO> response = orderService.getOrdersByReseller(resellerId, firstDate, lastDate);
    return ResponseEntity.ok().body(response);
  }

  @PatchMapping("/atualizar-status")
  public ResponseEntity<String> updateOrderStatus(@RequestBody UpdateOrderStatusDTO dto) {
    orderService.updateOrderStatus(dto);
    return ResponseEntity.ok("Status do pedido atualizado com sucesso!");
  }

}
