package com.clothify.mapper;

import com.clothify.domain.product.Product;
import com.clothify.payload.request.product.ProductRequest;
import com.clothify.payload.response.product.SimpleProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  SimpleProductResponse toSimpleProductResponse(Product product);

  @Mapping(target = "attributes", ignore = true)
  Product updateProduct(ProductRequest productRequest, @MappingTarget Product product);
}
