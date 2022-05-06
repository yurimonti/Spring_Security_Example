package com.example.Spring_Security_Example.repository;

import com.example.Spring_Security_Example.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<UserRole,Long> {
    UserRole findByName(String name);
}
