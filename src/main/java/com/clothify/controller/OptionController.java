package com.clothify.controller;

import com.clothify.domain.product.Option;
import com.clothify.service.product.OptionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/options")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class OptionController {
  private final OptionService optionService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Option createOption(@RequestBody @Valid Option option) {
    return optionService.createOption(option);
  }

  @GetMapping
  public List<Option> findAllOption() {
    return optionService.findAllOption();
  }

  @PutMapping("/{id}")
  public Option updateOption(@PathVariable("id") UUID id, @RequestBody @Valid Option option) {
    return optionService.updateOption(id, option.getName());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateOption(@PathVariable("id") UUID id) {
    optionService.deleteOption(id);
  }
}
