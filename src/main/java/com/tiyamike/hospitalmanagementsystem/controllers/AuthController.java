package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.AppUser;
import com.tiyamike.hospitalmanagementsystem.services.AppUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private final BCryptPasswordEncoder passwordEncoder;
    private final AppUserService appUserService;

    public AuthController(BCryptPasswordEncoder passwordEncoder,
                          AppUserService appUserService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
    }

    @GetMapping("/Login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "auth/login";
    }

    @GetMapping("/ChangePassword")
    public String changePassword() {
        return "auth/change-password";
    }

    @PostMapping("/UpdatePassword")
    public String passwordUpdate(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 RedirectAttributes redirectAttributes) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        AppUser currentUser = appUserService.getUserByUsername(username);

        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            appUserService.updateUserPassword(currentUser);
            redirectAttributes.addFlashAttribute("message", "Password was updated successfully!." +
                    " On your next login, you will use the new updated password");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("message", "Old Password is not correct!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/ChangePassword";
        }
    }
}
