package com.clothify.service.product;

import com.clothify.domain.product.Option;
import com.clothify.domain.product.Product;
import com.clothify.domain.product.ProductOption;
import com.clothify.domain.product.ProductOptionEntityGraph;
import com.clothify.payload.request.product.ProductOptionsDTO;
import com.clothify.repository.OptionRepository;
import com.clothify.repository.ProductOptionRepository;
import com.clothify.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductOptionServiceImpl implements ProductOptionService {
  private final ProductOptionRepository productOptionRepository;
  private final ProductRepository productRepository;
  private final OptionRepository optionRepository;

  @Override
  @Transactional
  public ProductOption createProductOption(UUID productId, UUID optionId, String value) {
    Product product = productRepository.getReferenceById(productId);
    Option option = optionRepository.getReferenceById(optionId);
    ProductOption productOption = new ProductOption();
    productOption.setProduct(product);
    productOption.setOption(option);
    productOption.setValue(value);
    return productOptionRepository.save(productOption);
  }

  @Override
  @Transactional
  public void batchInsertProductOption(UUID productId, UUID optionId, List<String> values) {
    Product product = productRepository.getReferenceById(productId);
    Option option = optionRepository.getReferenceById(optionId);
    List<ProductOption> productOptions = new ArrayList<>();
    for (String value : values) {
      ProductOption productOption = new ProductOption();
      productOption.setProduct(product);
      productOption.setOption(option);
      productOption.setValue(value);
      productOptions.add(productOption);
    }
    productOptionRepository.saveAll(productOptions);
  }

  @Override
  @Transactional
  public List<ProductOption> batchInsertProductOption(
      UUID productId, List<ProductOptionsDTO> productOptionsDTOS) {
    List<ProductOption> productOptions = new ArrayList<>();
    Product product = productRepository.getReferenceById(productId);
    for (var productOptionsDTO : productOptionsDTOS) {
      Option option = optionRepository.getReferenceById(productOptionsDTO.getOptionId());
      for (String value : productOptionsDTO.getValues()) {
        ProductOption productOption = new ProductOption();
        productOption.setProduct(product);
        productOption.setOption(option);
        productOption.setOptionId(productOptionsDTO.getOptionId());
        productOption.setValue(value);
        productOptions.add(productOption);
      }
    }
    return productOptionRepository.saveAll(productOptions);
  }

  @Override
  public List<ProductOption> findAllByProduct(Product product) {
    return productOptionRepository.findAllByProduct(
        product, ProductOptionEntityGraph.____().option().____.____());
  }

  @Override
  @Transactional
  public void deleteAllByProductId(UUID productId) {
    productOptionRepository.deleteAllByProductId(productId);
  }
}
