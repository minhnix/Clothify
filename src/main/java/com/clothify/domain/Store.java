package com.clothify.domain;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import com.clothify.domain.address.City;
import com.clothify.domain.address.District;
import com.clothify.domain.address.Ward;
import com.clothify.domain.enumuration.OpenStatus;
import com.clothify.domain.user.User;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.Type;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store extends AbstractAuditing {

  @Id @GeneratedValue private UUID id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column private String phoneNumber;

  @Column(columnDefinition = "text")
  private String name;

  @Column(columnDefinition = "text")
  private String logo;

  @Column(columnDefinition = "text")
  private String description;

  @Type(ListArrayType.class)
  @Column(columnDefinition = "text[]")
  private List<String> branchImagesDescription = new ArrayList<>();

  @Type(ListArrayType.class)
  @Column(columnDefinition = "text[]")
  private List<String> socialUrls = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private OpenStatus openStatus;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id")
  private District district;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ward_id")
  private Ward ward;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city_id")
  private City city;

  @Column private String street;

  private Timestamp openTime;

  private Timestamp closeTime;
}
