package com.clothify.service.product;

import com.clothify.domain.product.Option;

import java.util.List;
import java.util.UUID;

public interface OptionService {
    Option createOption(Option option);
    Option updateOption(UUID id, String newName);
    List<Option> findAllOption();
    void deleteOption(UUID id);
}

