package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.LabResult;
import com.tiyamike.hospitalmanagementsystem.models.Patient;
import com.tiyamike.hospitalmanagementsystem.models.PatientCase;
import com.tiyamike.hospitalmanagementsystem.services.PatientCaseService;
import com.tiyamike.hospitalmanagementsystem.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/PatientCases")
public class PatientCaseController {
    private final PatientCaseService patientCaseService;
    private final PatientService patientService;

    public PatientCaseController(PatientCaseService patientCaseService, PatientService patientService) {
        this.patientCaseService = patientCaseService;
        this.patientService = patientService;
    }

    @GetMapping("/All")
    public String getAllPatientCases(Model model){
        List<PatientCase> patientCaseList = patientCaseService.getPatientCases();
        model.addAttribute("patientCases", patientCaseList);
        return "patient_case/index";
    }

    @GetMapping("/Create")
    public String addPatientCase(Model model){
        List<Patient> patientList = patientService.getPatients();
        model.addAttribute("patients", patientList);
        return "patient_case/create";
    }

    @PostMapping("/Add")
    public String savePatientCase(PatientCase patientCase, RedirectAttributes redirectAttributes){
        patientCaseService.savePatientCase(patientCase);
        redirectAttributes.addFlashAttribute("message", "Patient Case was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/PatientCases/All";
    }

    @GetMapping("/Edit/{id}")
    public String editPatientCase(Model model, @PathVariable("id") long id){
        Optional<PatientCase> patientCase = patientCaseService.findById(id);
        List<Patient> patientList = patientService.getPatients();
        model.addAttribute("patients", patientList);
        model.addAttribute("patientCase", patientCase);
        return "patient_case/edit";
    }

    @PostMapping("/Update")
    public String updatePatientCase(PatientCase patientCase, RedirectAttributes redirectAttributes){
        patientCaseService.updatePatientCase(patientCase);
        redirectAttributes.addFlashAttribute("message", "Patient Case was updated successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/PatientCases/All";
    }

    @RequestMapping(value = "/Delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deletePatientCase(@PathVariable long id, RedirectAttributes redirectAttributes){
        patientCaseService.deletePatientCase(id);
        redirectAttributes.addFlashAttribute("message", "Patient Case was removed successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/PatientCases/All";
    }
}
