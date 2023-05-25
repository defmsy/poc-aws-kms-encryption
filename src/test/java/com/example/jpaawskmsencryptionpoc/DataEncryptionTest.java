package com.example.jpaawskmsencryptionpoc;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpaawskmsencryptionpoc.entities.User;
import com.example.jpaawskmsencryptionpoc.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class DataEncryptionTest {

  public static final String NAME = "John Doe";
  public static final String EMAIL = "john.doe@test.ca";

  private static final Logger LOGGER = LoggerFactory.getLogger(DataEncryptionTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private UserRepository userRepository;

  private Long id;

  @BeforeEach
  public void setUp() {
    User user = new User();
    user.setName(NAME);
    user.setEmail(EMAIL);
    this.id = this.userRepository.save(user).getId();
  }

  @Test
  void readDecrypted() {
    var userResult = this.userRepository.findById(id);
    assertThat(userResult.isPresent()).isTrue();
    var user = userResult.get();
    assertThat(user.getName()).isEqualTo(NAME);
    assertThat(user.getEmail()).isEqualTo(EMAIL);
  }

  @Test
  void readEncrypted() {
    var user = this.jdbcTemplate.queryForObject(
        "select * from user where id = ?",
        User.class,
        id
    );
    assertThat(user).isNotNull();
    assertThat(user.getName()).isNotEqualTo(NAME);
    LOGGER.info("Encrypted name value in DB is {}", user.getName());
    assertThat(user.getEmail()).isNotEqualTo(EMAIL);
    LOGGER.info("Encrypted email value in DB is {}", user.getEmail());
  }
}
