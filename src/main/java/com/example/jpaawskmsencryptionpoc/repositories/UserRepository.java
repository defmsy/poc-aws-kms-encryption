package com.example.jpaawskmsencryptionpoc.repositories;

import com.example.jpaawskmsencryptionpoc.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
