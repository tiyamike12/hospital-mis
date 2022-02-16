package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.AppUser;
import com.tiyamike.hospitalmanagementsystem.services.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Users")
public class UserController {
    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/All")
    public String getAllUsers(Model model){
        List<AppUser> userList = appUserService.getUsers();
        model.addAttribute("users", userList);
        return "users/index";
    }

    @GetMapping("/Create")
    public String addUser(){
        return "users/create";
    }

    @PostMapping("/Add")
    public String saveUser(AppUser appUser){
        appUserService.saveUser(appUser);
        return "redirect:/Users/All";
    }

    @GetMapping("/Edit/{id}")
    public String editUser(Model model, @PathVariable("id") long id){
        Optional<AppUser> user = appUserService.findById(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/Update")
    public String updateUser(AppUser appUser){
        appUserService.updateUser(appUser);
        return "redirect:/Users/All";
    }

    @RequestMapping(value = "/Delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteUser(@PathVariable long id){
        appUserService.deleteUser(id);
        return "redirect:/Users/All";
    }
}
