package com.example.Spring_Security_Example.repository;

import com.example.Spring_Security_Example.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<AppUser, UUID> {
    AppUser findByUsername(String username);

}
