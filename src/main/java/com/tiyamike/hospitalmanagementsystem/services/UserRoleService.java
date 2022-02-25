package com.tiyamike.hospitalmanagementsystem.services;

import com.tiyamike.hospitalmanagementsystem.models.UserRole;
import com.tiyamike.hospitalmanagementsystem.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void saveUserRole(UserRole userRole){
        userRoleRepository.save(userRole);
    }

    public List<UserRole> getUserRoles(){
        return userRoleRepository.findAll();
    }

    public Optional<UserRole> findById(long id){
        return userRoleRepository.findById(id);
    }

    public void updateUserRole(UserRole userRole){
        userRoleRepository.save(userRole);
    }

    public void deleteUserRole(long id){
        userRoleRepository.deleteById(id);
    }

    public List<UserRole> findAllByRole(){
        return userRoleRepository.findAllByRole();
    }
}
