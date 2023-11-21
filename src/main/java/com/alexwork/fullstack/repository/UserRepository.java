package com.alexwork.fullstack.repository;

import com.alexwork.fullstack.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
}
