package com.example.Spring_Security_Example.controller;

import com.example.Spring_Security_Example.model.AppUser;
import com.example.Spring_Security_Example.model.UserRole;
import com.example.Spring_Security_Example.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyRestController {
    private final MyUserService<AppUser, UserRole> userService;

    @GetMapping("/user")
    public ResponseEntity<AppUser> getUser(){
        return ResponseEntity.ok().body(userService.getUserByUsername("yuri"));
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<AppUser>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

}
