package com.example.Spring_Security_Example.service;

import com.example.Spring_Security_Example.model.AppUser;
import com.example.Spring_Security_Example.model.UserRole;
import com.example.Spring_Security_Example.repository.RoleRepo;
import com.example.Spring_Security_Example.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class MyUserServiceImp implements MyUserService<AppUser,UserRole>, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser myUser = getUserByUsername(username);
        if(myUser == null) throw new UsernameNotFoundException("username not corresponds to any user");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        myUser.getRoles().stream().map(userRole -> new SimpleGrantedAuthority(userRole.getName()))
                .forEach(authorities::add);
        return new User(username,myUser.getPassword(),authorities);
    }

    @Override
    public AppUser getUserByUsername(String username) {
       return userRepo.findByUsername(username);
    }

    @Override
    public Collection<AppUser> getUsers() {
        return userRepo.findAll();
    }

    private boolean userContainsRole(String username, UserRole role){
        return getUserByUsername(username).getRoles().contains(role);
        /*return user.getRoles().stream().anyMatch(r -> Objects.equals(r.getName(), role.getName()));*/
    }

    @Override
    public void addRoleToUser(String username, UserRole role) {
        AppUser myUser = getUserByUsername(username);
        if(!userContainsRole(myUser.getUsername(),role)){
            myUser.getRoles().add(role);
            userRepo.save(myUser);
        }

    }

    @Override
    public void removeRoleFromUser(String username, UserRole role) {
        AppUser myUser = getUserByUsername(username);
        if(userContainsRole(username,role)){
            myUser.getRoles().remove(role);
            userRepo.save(myUser);
        }
    }

    @Override
    public void addNewUser(AppUser user) {
        userRepo.save(user);
    }

    @Override
    public void removeUser(AppUser user) {
        AppUser myUser = getUserByUsername(user.getUsername());
        userRepo.delete(myUser);
    }

    @Override
    public UserRole getRoleByName(String name) {
        return roleRepo.findByName(name);
    }
}
