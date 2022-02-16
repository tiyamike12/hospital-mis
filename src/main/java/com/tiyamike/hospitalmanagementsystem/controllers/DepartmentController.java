package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.Department;
import com.tiyamike.hospitalmanagementsystem.services.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/All")
    public String getAllDepartments(Model model){
        List<Department> departmentList = departmentService.getDepartments();
        model.addAttribute("departments", departmentList);
        return "department/index";
    }

    @GetMapping("/Create")
    public String addDepartment(){
        return "department/create";
    }

    @PostMapping("/Add")
    public String saveDepartment(Department department, RedirectAttributes redirectAttributes){
        departmentService.saveDepartment(department);
        redirectAttributes.addFlashAttribute("message", "Department was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Departments/All";
    }

    @GetMapping("/Edit/{id}")
    public String editDepartment(Model model, @PathVariable("id") long id){
        Optional<Department> department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "department/edit";
    }

    @PostMapping("/Update")
    public String updateDepartment(Department department, RedirectAttributes redirectAttributes){
        departmentService.updateDepartment(department);
        redirectAttributes.addFlashAttribute("message", "Department was updated successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Departments/All";
    }

    @RequestMapping(value = "/Delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteDepartment(@PathVariable long id, RedirectAttributes redirectAttributes){
        departmentService.deleteDepartment(id);
        redirectAttributes.addFlashAttribute("message", "Department was removed successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/Departments/All";
    }
}
