package mdg.miguel.mdgproject.controllers;

import java.util.List;

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

  @GetMapping("/buscar")
  public ResponseEntity<List<ResellerDTO>> searchResellers(@RequestParam String name) {
    List<ResellerDTO> result = resellerService.searchByName(name);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/buscar/por-cidade")
  public ResponseEntity<List<ResellerDTO>> searchResellersByCity(@RequestParam Cities city) {
    List<ResellerDTO> result = resellerService.searchByCity(city);
    return ResponseEntity.ok(result);
  }

}
