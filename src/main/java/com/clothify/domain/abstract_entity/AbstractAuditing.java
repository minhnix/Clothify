package com.clothify.domain.abstract_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.UUID;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate", "deletedAt"},
    allowGetters = true)
public abstract class AbstractAuditing {
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Timestamp createdAt;

  @LastModifiedDate
  @Column(nullable = false)
  private Timestamp updatedAt;

  @CreatedBy
  @Column(updatable = false)
  private UUID createdBy;

  @LastModifiedBy private UUID updatedBy;
  private Timestamp deletedAt;
}
