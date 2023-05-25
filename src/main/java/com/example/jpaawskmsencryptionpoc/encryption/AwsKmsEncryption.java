package com.example.jpaawskmsencryptionpoc.encryption;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.kmssdkv2.AwsKmsMrkAwareMasterKeyProvider;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.springframework.stereotype.Component;

@Component
public class AwsKmsEncryption {

  private final AwsCrypto awsCrypto;
  private final AwsKmsMrkAwareMasterKeyProvider kmsMasterKeyProvider;

  public AwsKmsEncryption(AwsCrypto awsCrypto, AwsKmsMrkAwareMasterKeyProvider kmsMasterKeyProvider) {
    this.awsCrypto = awsCrypto;
    this.kmsMasterKeyProvider = kmsMasterKeyProvider;
  }

  public String encrypt(String data) {
    var encryptResult = this.awsCrypto.encryptData(this.kmsMasterKeyProvider, data.getBytes(StandardCharsets.UTF_8));
    return Arrays.toString(encryptResult.getResult());
  }

  public String decrypt(String data) {
    var decryptResult = this.awsCrypto.decryptData(this.kmsMasterKeyProvider, data.getBytes(
        StandardCharsets.UTF_8));
    return Arrays.toString(decryptResult.getResult());
  }
}
