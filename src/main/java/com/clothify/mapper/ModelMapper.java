package com.clothify.mapper;

import com.clothify.domain.product.Model;
import com.clothify.payload.response.product.ModelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {
  ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

  @Mapping(target = "productId", source = "source.product.id")
  @Mapping(target = "stock", source = "source.inventory.stock")
  @Mapping(target = "name", source = "source.product.name")
  @Mapping(target = "modelId", source = "source.id")
  ModelResponse mapToModelResponse(Model source);
}
