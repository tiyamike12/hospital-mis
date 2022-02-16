package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.Department;
import com.tiyamike.hospitalmanagementsystem.models.Nurse;
import com.tiyamike.hospitalmanagementsystem.services.DepartmentService;
import com.tiyamike.hospitalmanagementsystem.services.NurseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Nurses")
public class NurseController {

    private final NurseService nurseService;
    private final DepartmentService departmentService;

    public NurseController(NurseService nurseService, DepartmentService departmentService) {
        this.nurseService = nurseService;
        this.departmentService = departmentService;
    }

    @GetMapping("/All")
    public String getAllNurses(Model model){
        List<Nurse> nurseList = nurseService.getNurses();
        model.addAttribute("nurses", nurseList);
        return "nurse/index";
    }

    @GetMapping("/Create")
    public String addNurse(Model model){
        List<Department> departmentList = departmentService.getDepartments();
        model.addAttribute("departments", departmentList);
        return "nurse/create";
    }

    @PostMapping("/Add")
    public String saveNurse(Nurse nurse, RedirectAttributes redirectAttributes){
        nurseService.saveNurse(nurse);
        redirectAttributes.addFlashAttribute("message", "Nurse was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Nurses/All";
    }

    @GetMapping("/Edit/{id}")
    public String editNurse(Model model, @PathVariable("id") long id){
        Optional<Nurse> nurse = nurseService.findById(id);
        List<Department> departmentList = departmentService.getDepartments();
        model.addAttribute("departments", departmentList);
        model.addAttribute("nurse", nurse);
        return "nurse/edit";
    }

    @PostMapping("/Update")
    public String updateNurse(Nurse nurse, RedirectAttributes redirectAttributes){
        nurseService.updateNurse(nurse);
        redirectAttributes.addFlashAttribute("message", "Nurse was updated successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Nurses/All";
    }

    @RequestMapping(value = "/Delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteNurse(@PathVariable long id, RedirectAttributes redirectAttributes){
        nurseService.deleteNurse(id);
        redirectAttributes.addFlashAttribute("message", "Nurse was removed successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/Nurses/All";
    }
}
