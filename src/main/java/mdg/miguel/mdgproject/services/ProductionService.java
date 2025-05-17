package mdg.miguel.mdgproject.services;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import mdg.miguel.mdgproject.dtos.ProductionDTO;
import mdg.miguel.mdgproject.dtos.ProductionResponseDTO;
import mdg.miguel.mdgproject.entities.DailyProduction;
import mdg.miguel.mdgproject.exceptions.ProductionNotFoundException;
import mdg.miguel.mdgproject.repositories.ProductionRepository;
import mdg.miguel.mdgproject.services.validators.ProductionValidator;

@Service
public class ProductionService {

  private ProductionRepository productionRepository;
  private ProductionValidator validator;

  public ProductionService(ProductionRepository productionRepository, ProductionValidator validator) {
    this.productionRepository = productionRepository;
    this.validator = validator;
  }

  public void save(ProductionDTO dto) {
    validator.validate(dto);

    var production = new DailyProduction();
    BeanUtils.copyProperties(dto, production);
    productionRepository.save(production);
  }

  public ProductionResponseDTO getProduction(LocalDate date) {
    DailyProduction production = productionRepository.findByDate(date)
        .orElseThrow(() -> new ProductionNotFoundException("Não há produção na data de: " + date));

    var response = new ProductionResponseDTO();
    BeanUtils.copyProperties(production, response);
    return response;

  }

}
