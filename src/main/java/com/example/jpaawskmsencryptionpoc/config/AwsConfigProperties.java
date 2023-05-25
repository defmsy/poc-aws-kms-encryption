package com.example.jpaawskmsencryptionpoc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws")
@Data
public class AwsConfigProperties {

  private KmsConfigProperties kms;

  @Data
  public static class KmsConfigProperties {
    private String keyArn;
  }
}
