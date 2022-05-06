package com.example.Spring_Security_Example;

import com.example.Spring_Security_Example.model.AppUser;
import com.example.Spring_Security_Example.model.UserRole;
import com.example.Spring_Security_Example.repository.RoleRepo;
import com.example.Spring_Security_Example.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DbConfig {
    @Bean
    CommandLineRunner initDatabase(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder encoder){
        return args -> {
            AppUser user = new AppUser("yuri","yuri", encoder.encode("yuri"));
            UserRole simple = new UserRole("ROLE_USER");
            UserRole adminRole= new UserRole("ROLE_ADMIN");
            user.getRoles().add(simple);
            roleRepo.save(simple);
            roleRepo.save(adminRole);
            userRepo.save(user);
        };
    }
}
