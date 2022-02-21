package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.AppUser;
import com.tiyamike.hospitalmanagementsystem.services.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String addUser(AppUser appUser){
        return "users/create";
    }

    @PostMapping("/Add")
    public String saveUser(@Valid AppUser appUser, Errors errors, RedirectAttributes redirectAttributes){
        if (errors.hasErrors()){
            return "users/create";
        }
        appUserService.saveUser(appUser);
        redirectAttributes.addFlashAttribute("message", "User was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Users/All";
    }

    @GetMapping("/Edit/{id}")
    public String editUser(Model model, @PathVariable("id") long id){
        Optional<AppUser> user = appUserService.findById(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @RequestMapping(value = "/Update", method = {RequestMethod.PATCH, RequestMethod.POST})
    public String updateUser(AppUser appUser, RedirectAttributes redirectAttributes){
        appUserService.updateUser(appUser);
        redirectAttributes.addFlashAttribute("message", "User updated successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Users/All";
    }

    @RequestMapping(value = "/Delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteUser(@PathVariable long id, RedirectAttributes redirectAttributes){
        appUserService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User was deleted successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/Users/All";
    }
}
