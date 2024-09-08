package com.clothify.domain.user;

import com.clothify.domain.abstract_entity.AbstractAuditing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_infos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends AbstractAuditing {
  @Id @GeneratedValue private UUID id;
  private String firstname;
  private String lastname;
  private String avatar;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
}
