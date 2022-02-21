package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.Department;
import com.tiyamike.hospitalmanagementsystem.models.Doctor;
import com.tiyamike.hospitalmanagementsystem.services.DepartmentService;
import com.tiyamike.hospitalmanagementsystem.services.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    public DoctorController(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    @GetMapping("/All")
    public String getAllDoctors(Model model){
        List<Doctor> doctorList = doctorService.getDoctors();
        model.addAttribute("doctors", doctorList);
        return "doctor/index";
    }

    @GetMapping("/Create")
    public String addDoctor(Doctor doctor, Model model){
        List<Department> departmentList = departmentService.getDepartments();
        model.addAttribute("departments", departmentList);
        return "doctor/create";
    }

    @PostMapping("/Add")
    public String saveDoctor(@Valid Doctor doctor, Errors errors, RedirectAttributes redirectAttributes){
        if (errors.hasErrors()){
            return "doctor/create";
        }
        doctorService.saveDoctor(doctor);
        redirectAttributes.addFlashAttribute("message", "Doctor was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Doctors/All";
    }

    @GetMapping("/Edit/{id}")
    public String editDoctor(Model model, @PathVariable("id") long id){
        Optional<Doctor> doctor = doctorService.findById(id);
        List<Department> departmentList = departmentService.getDepartments();
        model.addAttribute("departments", departmentList);
        model.addAttribute("doctor", doctor);
        return "doctor/edit";
    }

    @PostMapping("/Update")
    public String updateDoctor(@Valid Doctor doctor, Errors errors,
                               RedirectAttributes redirectAttributes,
                               Model model){
        if (errors.hasErrors()){
            List<Department> departmentList = departmentService.getDepartments();
            model.addAttribute("departments", departmentList);
            return "doctor/edit";
        }
        doctorService.updateDoctor(doctor);
        redirectAttributes.addFlashAttribute("message", "Doctor was updated successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Doctors/All";
    }

    @RequestMapping(value = "/Delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteDoctor(@PathVariable long id, RedirectAttributes redirectAttributes){
        doctorService.deleteDoctor(id);
        redirectAttributes.addFlashAttribute("message", "Doctor was removed successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/Doctors/All";
    }
}
