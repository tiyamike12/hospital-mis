package com.tiyamike.hospitalmanagementsystem.services;

import com.tiyamike.hospitalmanagementsystem.models.AppUser;
import com.tiyamike.hospitalmanagementsystem.repositories.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(AppUser appUser){
        appUser.setUsername(appUser.getUsername());
        appUser.setFirstname(appUser.getFirstname());
        appUser.setLastname(appUser.getLastname());
        appUser.setEmail(appUser.getEmail());
        appUser.setPassword(passwordEncoder.encode("!Password"));
        appUserRepository.save(appUser);
    }

    public void updateUserPassword(AppUser appUser){
        appUserRepository.save(appUser);
    }

    public List<AppUser> getUsers(){
        return appUserRepository.findAll();
    }

    public Optional<AppUser> findById(long id){
        return appUserRepository.findById(id);
    }

    public void updateUser(AppUser appUser){
        appUserRepository.save(appUser);
    }

    public void deleteUser(long id){
        appUserRepository.deleteById(id);
    }

    public AppUser getUserByUsername(String username) {
        return appUserRepository.findAppUserByUsername(username);
    }

    public AppUser findUserByUserId(long id) {
        return appUserRepository.findAppUserById(id);
    }
}
