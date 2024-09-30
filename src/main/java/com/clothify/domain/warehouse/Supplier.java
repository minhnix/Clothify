package com.clothify.domain.warehouse;

import com.clothify.domain.address.City;
import com.clothify.domain.address.District;
import com.clothify.domain.address.Ward;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
  @Id @GeneratedValue private UUID id;
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private City city;

  @ManyToOne(fetch = FetchType.LAZY)
  private District district;

  @ManyToOne(fetch = FetchType.LAZY)
  private Ward ward;

  private String street;
  private String address;
  private String phone;
  private String email;
  private String contactPerson;
  private String contactPersonPhone;
  private String contactPersonEmail;
}
