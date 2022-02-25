package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.AppUser;
import com.tiyamike.hospitalmanagementsystem.models.Role;
import com.tiyamike.hospitalmanagementsystem.models.UserRole;
import com.tiyamike.hospitalmanagementsystem.services.AppUserService;
import com.tiyamike.hospitalmanagementsystem.services.RoleService;
import com.tiyamike.hospitalmanagementsystem.services.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/UserRoles")
public class UserRoleController {
    private final UserRoleService userRoleService;
    private final AppUserService appUserService;
    private final RoleService roleService;

    public UserRoleController(UserRoleService userRoleService,
                              AppUserService appUserService, RoleService roleService) {
        this.userRoleService = userRoleService;
        this.appUserService = appUserService;
        this.roleService = roleService;
    }

    @GetMapping("/All")
    public String getAllUserRoles(Model model){
        List<UserRole> userRoles = userRoleService.findAllByRole();
        model.addAttribute("userRoles", userRoles);
        return "user_role/index";
    }

    @GetMapping("/Create")
    public String addUserRole(UserRole userRole, Model model){
        List<AppUser> users = appUserService.getUsers();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "user_role/create";
    }

    @PostMapping("/Add")
    public String saveUserRole(@Valid UserRole userRole, Errors errors,
                             Model model, RedirectAttributes redirectAttributes){
        if (errors.hasErrors()){
            List<AppUser> users = appUserService.getUsers();
            model.addAttribute("users", users);
            return "user_role/create";
        }
        userRoleService.saveUserRole(userRole);
        redirectAttributes.addFlashAttribute("message", "User Role was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/UserRoles/All";
    }
}
