package com.example.jpaawskmsencryptionpoc.entities;

import com.example.jpaawskmsencryptionpoc.encryption.AttributeEncryptor;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Convert(converter = AttributeEncryptor.class)
  private String name;
  @Convert(converter = AttributeEncryptor.class)
  private String email;
}
