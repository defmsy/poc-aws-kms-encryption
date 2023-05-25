package com.example.jpaawskmsencryptionpoc.config;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CommitmentPolicy;
import com.amazonaws.encryptionsdk.kmssdkv2.AwsKmsMrkAwareMasterKeyProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

  @Bean
  public AwsCrypto getAwsCrypto() {
    return AwsCrypto
        .builder()
        .withCommitmentPolicy(CommitmentPolicy.RequireEncryptRequireDecrypt)
        .build();
  }

  @Bean
  public AwsKmsMrkAwareMasterKeyProvider getKmsMasterKeyProvider(AwsConfigProperties awsConfigProperties) {
    return AwsKmsMrkAwareMasterKeyProvider
        .builder()
        .buildStrict(awsConfigProperties.getKms().getKeyArn());
  }
}
