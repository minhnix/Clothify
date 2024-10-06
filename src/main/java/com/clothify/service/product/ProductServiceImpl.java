package com.clothify.service.product;

import com.clothify.domain.product.*;
import com.clothify.exception.BadRequestException;
import com.clothify.exception.DuplicateEntityException;
import com.clothify.exception.NotFoundException;
import com.clothify.mapper.ProductMapper;
import com.clothify.payload.request.product.ProductRequest;
import com.clothify.payload.request.product.VariantRequest;
import com.clothify.payload.response.PagedResponse;
import com.clothify.payload.response.product.ProductDetailResponse;
import com.clothify.payload.response.product.SimpleProductResponse;
import com.clothify.repository.BrandRepository;
import com.clothify.repository.CategoryRepository;
import com.clothify.repository.InventoryProductRepository;
import com.clothify.repository.ProductRepository;
import com.clothify.security.CustomUserDetails;
import com.clothify.utils.JsonUtils;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
  private final CategoryRepository categoryRepository;
  private final BrandRepository brandRepository;
  private final ProductRepository productRepository;
  private final InventoryProductService inventoryProductService;
  private final InventoryProductRepository inventoryProductRepository;
  private final InventoryService inventoryService;
  private final ModelService modelService;
  private final ProductOptionService productOptionService;

  @Override
  @Transactional
  public Product createProduct(ProductRequest productRequest) {
    Product product = new Product();
    setBasicInfoProduct(product, productRequest);
    Product savedProduct = productRepository.save(product);
    if (productRequest.isVariant() && !productRequest.getVariants().isEmpty()) {
      if (productRequest.getOptions().isEmpty()) {
        throw new BadRequestException("Product options not set!");
      }
      List<ProductOption> productOptions =
          productOptionService.batchInsertProductOption(
              savedProduct.getId(), productRequest.getOptions());
      long totalStock =
          insertModelAndInventoryAndGetTotalStock(
              savedProduct, productRequest.getVariants(), productOptions);
      inventoryProductService.createInventoryProduct(savedProduct, totalStock);
    } else {
      inventoryProductService.createInventoryProduct(savedProduct, productRequest.getQuantity());
      Model model = modelService.createModelWithOutVariant(savedProduct);
      inventoryService.createInventory(model, productRequest.getQuantity());
    }
    return savedProduct;
  }

  @Transactional
  public SimpleProductResponse publishProduct(UUID id) {
    Product product =
        productRepository
            .findById(id, ProductEntityGraph.____().category().____.____())
            .orElseThrow(() -> new NotFoundException("Product not found"));
    product.setPublished(true);
    return ProductMapper.INSTANCE.toSimpleProductResponse(productRepository.save(product));
  }

  @Transactional
  public SimpleProductResponse unPublishProduct(UUID id) {
    Product product =
        productRepository
            .findById(id, ProductEntityGraph.____().category().____.____())
            .orElseThrow(
                () -> new NotFoundException(String.format("Product not found with id %s", id)));
    product.setPublished(false);
    return ProductMapper.INSTANCE.toSimpleProductResponse(productRepository.save(product));
  }

  public PagedResponse<SimpleProductResponse> findAllPublishProduct(Pageable pageable) {
    Page<SimpleProductResponse> products =
        productRepository
            .findAllByPublished(pageable, true)
            .map(ProductMapper.INSTANCE::toSimpleProductResponse);
    return new PagedResponse<>(products);
  }

  public PagedResponse<SimpleProductResponse> findAllUnPublishProduct(Pageable pageable) {
    Page<SimpleProductResponse> products =
        productRepository
            .findAllByPublished(pageable, false)
            .map(ProductMapper.INSTANCE::toSimpleProductResponse);
    return new PagedResponse<>(products);
  }

  @Override
  public PagedResponse<SimpleProductResponse> findAllProduct(Pageable pageable) {
    Page<SimpleProductResponse> products =
        productRepository
            .findAllByAdmin(pageable)
            .map(ProductMapper.INSTANCE::toSimpleProductResponse);
    return new PagedResponse<>(products);
  }

  @Override
  public SimpleProductResponse updateProduct(UUID productId, ProductRequest productRequest) {
    return null;
  }

  @Override
  public ProductDetailResponse findOneProduct(UUID id, CustomUserDetails user) {
    Product product =
        productRepository
            .findById(id, ProductEntityGraph.____().category().____.inventoryProduct().____.____())
            .orElseThrow(() -> new NotFoundException("Product not found"));
    if (!product.isPublished() && (user == null || !user.isAdmin())) {
      throw new NotFoundException("Product not found");
    }
    List<ProductOption> productOptions = productOptionService.findAllByProduct(product);
    List<Model> models = modelService.findAllByProductId(product.getId(), true);
    return ProductDetailResponse.toResponse(product, productOptions, models);
  }

  //  @Override
  //  @Transactional
  //  public SimpleProductResponse updateProduct(Long productId, ProductRequest productRequest) {
  //    Product product =
  //        productRepository
  //            .findById(productId, ProductEntityGraph.____().category().____.____())
  //            .orElseThrow(
  //                () ->
  //                    new NotFoundException(
  //                        String.format("Product not found with id %d", productId)));
  //    if (product.isVariant() && productRequest.isVariant()) {
  //      List<ProductOption> productOptions = productOptionService.findAllByProduct(product);
  //      List<Model> models = modelService.findAllByProductId(productId, false);
  //      // update model
  //    } else if (product.isVariant()) {
  //      modelService.deleteByProductId(productId);
  //      productOptionService.deleteAllByProductId(productId);
  //      Model model = modelService.createModel(product, null);
  //      inventoryService.createInventory(
  //          model, productRequest.getQuantity() == null ? 0L : productRequest.getQuantity());
  //      inventoryProductService.updateInventoryProduct(
  //          product.getId(),
  //          productRequest.getQuantity() == null ? 0L : productRequest.getQuantity(),
  //          null,
  //          null);
  //    } else if (productRequest.isVariant()) {
  //      if (productRequest.getOptions().isEmpty()) {
  //        throw new BadRequestException("Product options not set!");
  //      }
  //      // TODO: Check name valid with option
  //      productOptionService.batchInsertProductOption(product.getId(),
  // productRequest.getOptions());
  //      modelService.deleteByProductId(productId); // delete default model if no variant
  //      long totalStock =
  //          insertModelAndInventoryAndGetTotalStock(product, productRequest.getVariants());
  //      inventoryProductService.updateInventoryProduct(productId, totalStock, null, null);
  //    }
  //    updateBasicInfoProduct(product, productRequest);
  //    Product savedProduct = productRepository.save(product);
  //    return ProductMapper.INSTANCE.toSimpleProductResponse(savedProduct);
  //  }

  private Long insertModelAndInventoryAndGetTotalStock(
      Product product, List<VariantRequest> variantRequests, List<ProductOption> productOptions) {
    long totalStock = 0;
    for (var variant : variantRequests) {
      Model model = modelService.createModel(product, variant, productOptions);
      long inventoryStock = variant.getQuantity() != null ? variant.getQuantity() : 0;
      inventoryService.createInventory(model, inventoryStock);
      totalStock += inventoryStock;
    }
    return totalStock;
  }

  @Override
  @Transactional
  public void deleteProduct(UUID productId) {
    modelService.deleteByProductId(productId);
    productOptionService.deleteAllByProductId(productId);
    inventoryProductRepository.deleteByProductId(productId);
    productRepository.deleteById(productId);
  }

  private void setBasicInfoProduct(Product product, ProductRequest productRequest) {
    Category category =
        categoryRepository
            .findById(productRequest.getCategoryId())
            .orElseThrow(() -> new NotFoundException("Category not found"));
    Brand brand =
        brandRepository
            .findById(productRequest.getBrandId())
            .orElseThrow(() -> new NotFoundException("Brand not found"));
    boolean existName = productRepository.existsByName(productRequest.getName());
    if (existName) throw new DuplicateEntityException("Product with the same name already exists");
    product.setCategory(category);
    product.setBrand(brand);
    ProductMapper.INSTANCE.updateProduct(productRequest, product);
    product.setAttributes(JsonUtils.objectToJsonString(productRequest.getAttributes()));
  }

  private void updateBasicInfoProduct(Product product, ProductRequest productRequest) {
    if (!product.getName().equals(productRequest.getName())) {
      boolean existName = productRepository.existsByName(productRequest.getName());
      if (existName)
        throw new DuplicateEntityException("Product with the same name already exists");
    }
    if (!product.getCategory().getId().equals(productRequest.getCategoryId())) {
      Category category =
          categoryRepository
              .findById(productRequest.getCategoryId())
              .orElseThrow(
                  () ->
                      new NotFoundException(
                          String.format(
                              "Category not found with id %s", productRequest.getCategoryId())));
      product.setCategory(category);
    }
    ProductMapper.INSTANCE.updateProduct(productRequest, product);
    product.setAttributes(JsonUtils.objectToJsonString(productRequest.getAttributes()));
  }
}
