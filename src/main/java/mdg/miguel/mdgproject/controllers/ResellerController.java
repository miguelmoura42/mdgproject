package mdg.miguel.mdgproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mdg.miguel.mdgproject.dtos.ResellerDTO;
import mdg.miguel.mdgproject.enums.Cities;
import mdg.miguel.mdgproject.services.ResellerService;

@RestController
@RequestMapping("/revendedores")
public class ResellerController {

  private ResellerService resellerService;

  public ResellerController(ResellerService resellerService) {
    this.resellerService = resellerService;
  }

  @PostMapping("/novo-revendedor")
  public ResponseEntity<String> newReseller(@RequestBody @Valid ResellerDTO dto) {
    resellerService.save(dto);
    return ResponseEntity.ok("Revendedor cadastrado com sucesso!");
  }

  @GetMapping("/busca")
  public ResponseEntity<Page<ResellerDTO>> searchResellers(
      @RequestParam String name,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
    Page<ResellerDTO> result = resellerService.searchByName(name, pageable);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/busca-por-cidade")
  public ResponseEntity<Page<ResellerDTO>> searchResellersByCity(
      @RequestParam Cities city,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
    Page<ResellerDTO> result = resellerService.searchByCity(city, pageable);
    return ResponseEntity.ok(result);
  }

}
