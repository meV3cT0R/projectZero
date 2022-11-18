package com.vector.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vector.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
