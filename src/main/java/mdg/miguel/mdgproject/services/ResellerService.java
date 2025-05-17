package mdg.miguel.mdgproject.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mdg.miguel.mdgproject.dtos.ResellerDTO;
import mdg.miguel.mdgproject.entities.Reseller;
import mdg.miguel.mdgproject.enums.Cities;
import mdg.miguel.mdgproject.exceptions.ResellerNotFoundException;
import mdg.miguel.mdgproject.repositories.ResellerRepository;
import mdg.miguel.mdgproject.services.validators.ResellerValidator;

@Service
public class ResellerService {

  private ResellerRepository resellerRepository;
  private ResellerValidator resellerValidator;

  public ResellerService(ResellerRepository resellerRepository, ResellerValidator resellerValidator) {
    this.resellerRepository = resellerRepository;
    this.resellerValidator = resellerValidator;
  }

  public void save(ResellerDTO dto) {
    resellerValidator.validateDto(dto);
    var reseller = new Reseller();
    dto.setName(dto.getName().toUpperCase());
    BeanUtils.copyProperties(dto, reseller);
    resellerRepository.save(reseller);
  }

  public Page<ResellerDTO> searchByName(String name, Pageable pageable) {
    Page<Reseller> resellers = resellerRepository.findByNameContainingIgnoreCase(name, pageable);
    if (resellers.isEmpty()) {
      throw new ResellerNotFoundException("Nenhum revendedor encontrado com o nome: " + name);
    }
    return resellers.map(this::convertToDto);
  }

  public Page<ResellerDTO> searchByCity(Cities city, Pageable pageable) {
    Page<Reseller> resellers = resellerRepository.findByCity(city, pageable);
    if (resellers.isEmpty()) {
      throw new ResellerNotFoundException("Nenhum revendedor encontrado na cidade: " + city);
    }
    return resellers.map(this::convertToDto);
  }

  public Reseller getAndValidateReseller(String uniqueKey) {
    return resellerRepository.findByUniqueKey(uniqueKey)
        .orElseThrow(
            () -> new ResellerNotFoundException("Revendedor com chave única " + uniqueKey + " não encontrado."));
  }

  private ResellerDTO convertToDto(Reseller reseller) {
    var response = new ResellerDTO();
    BeanUtils.copyProperties(reseller, response);
    return response;
  }
}
