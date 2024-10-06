package com.clothify.service.product;

import com.clothify.domain.product.Option;
import com.clothify.exception.DuplicateEntityException;
import com.clothify.exception.NotFoundException;
import com.clothify.repository.OptionRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OptionServiceImpl implements OptionService {
  private final OptionRepository optionRepository;

  @Override
  @Transactional
  public Option createOption(Option option) {
    boolean exist = optionRepository.existsByName(option.getName());
    if (exist) throw new DuplicateEntityException("An Option with the same name already exists");
    return optionRepository.save(option);
  }

  @Override
  public Option updateOption(UUID id, String newName) {
    Option option =
        optionRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Option not found with id: " + id));
    boolean exist = optionRepository.existsByName(option.getName());
    if (exist) throw new DuplicateEntityException("An Option with the same name already exists");
    option.setName(newName);
    return optionRepository.save(option);
  }

  @Override
  public List<Option> findAllOption() {
    return optionRepository.findAll();
  }

  @Override
  public void deleteOption(UUID id) {
    optionRepository.deleteById(id);
  }

  public Option getOne(UUID id) {
    return optionRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Option not found with id: " + id));
  }
}
