package com.example.Spring_Security_Example.service;

import com.example.Spring_Security_Example.model.AppUser;
import com.example.Spring_Security_Example.model.UserRole;

import java.util.Collection;

public interface MyUserService<T extends AppUser,R extends UserRole> {
    T getUserByUsername(String username);

    Collection<T> getUsers();

    void addRoleToUser(String username, R role);

    void removeRoleFromUser(String username,R role);

    void addNewUser(T user);

    void removeUser(T user);

    public R getRoleByName(String name);

}
