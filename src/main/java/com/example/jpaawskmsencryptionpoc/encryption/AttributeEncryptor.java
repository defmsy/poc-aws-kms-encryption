package com.example.jpaawskmsencryptionpoc.encryption;

import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

@Component
public class AttributeEncryptor implements AttributeConverter<String, String> {

  private final AwsKmsEncryption awsKmsEncryption;

  public AttributeEncryptor(AwsKmsEncryption awsKmsEncryption) {
    this.awsKmsEncryption = awsKmsEncryption;
  }

  @Override
  public String convertToDatabaseColumn(String attribute) {
    return this.awsKmsEncryption.encrypt(attribute);
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    return this.awsKmsEncryption.decrypt(dbData);
  }
}
