package com.clothify.domain.product;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.warehouse.InventoryProduct;
import com.clothify.utils.SlugUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(
    name = "products",
    indexes = {
      @Index(name = "index_product_name", columnList = "product_name"),
      @Index(name = "index_product_slug", columnList = "product_slug"),
      @Index(name = "index_product_is_published", columnList = "product_is_published")
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractAuditing {
  @Id
  @GeneratedValue
  @Column(name = "product_id")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "brand_id", nullable = false)
  private Brand brand;

  @Column(name = "product_name", nullable = false)
  private String name;

  @Column(name = "product_slug", nullable = false, unique = true)
  private String slug;

  @Column(name = "product_description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "product_image")
  private String image;

  @Column(name = "product_base_price")
  private Long basePrice;

  @Column(name = "product_price")
  private Long price;

  @JsonIgnore
  @Column(name = "product_is_published")
  private boolean isPublished = false;

  @Type(JsonType.class)
  @Column(columnDefinition = "JSON")
  private Map<String, String> attributes;

  @OneToOne(
      fetch = FetchType.LAZY,
      mappedBy = "product",
      optional = false,
      cascade = CascadeType.ALL)
  private InventoryProduct inventoryProduct;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Model> models = new ArrayList<>();

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ProductOption> productOptions = new ArrayList<>();

  @PrePersist
  private void createSlug() {
    slug = SlugUtils.createSlug(name);
  }
}
