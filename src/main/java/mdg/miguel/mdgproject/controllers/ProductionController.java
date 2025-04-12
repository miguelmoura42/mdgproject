package mdg.miguel.mdgproject.controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mdg.miguel.mdgproject.dtos.ProductionDTO;
import mdg.miguel.mdgproject.dtos.ProductionResponseDTO;
import mdg.miguel.mdgproject.services.ProductionService;

@RestController
@RequestMapping("/producao")
public class ProductionController {

  private ProductionService productionService;

  public ProductionController(ProductionService productionService) {
    this.productionService = productionService;
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<String> newDailyProduction(@RequestBody ProductionDTO dto) {
    productionService.save(dto);
    return ResponseEntity.ok("Produção do dia " + dto.getDate() + " salva com sucesso!");
  }

  @GetMapping("/buscar")
  public ResponseEntity<ProductionResponseDTO> getProduction(
      @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date) {

    ProductionResponseDTO response = productionService.getProduction(date);
    return ResponseEntity.ok(response);
  }

}
