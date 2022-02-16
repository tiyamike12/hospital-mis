package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.Gender;
import com.tiyamike.hospitalmanagementsystem.models.Patient;
import com.tiyamike.hospitalmanagementsystem.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/All")
    public String getAllPatients(Model model){
        List<Patient> patientList = patientService.getPatients();
        model.addAttribute("patients", patientList);
        return "patient/index";
    }

    @GetMapping("/Create")
    public String addPatient(Model model){
        model.addAttribute("gender", Gender.values());
        return "patient/create";
    }

    @PostMapping("/Add")
    public String savePatient(Patient patient, RedirectAttributes redirectAttributes){
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("message", "Patient was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Patients/All";
    }

    @GetMapping("/Edit/{id}")
    public String editPatient(Model model, @PathVariable("id") long id){
        Optional<Patient> patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "patient/edit";
    }

    @PostMapping("/Update")
    public String updatePatient(Patient patient, RedirectAttributes redirectAttributes){
        patientService.updatePatient(patient);
        redirectAttributes.addFlashAttribute("message", "Patient was updated successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Patients/All";
    }

    @RequestMapping(value = "/Delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deletePatient(@PathVariable long id, RedirectAttributes redirectAttributes){
        patientService.deletePatient(id);
        redirectAttributes.addFlashAttribute("message", "Patient was removed successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/Patients/All";
    }
}