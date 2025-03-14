package com.projectjava.demo.repository;

import com.projectjava.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email); // ✅ Fix: Add this method
}
