package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.Doctor;
import com.tiyamike.hospitalmanagementsystem.models.Patient;
import com.tiyamike.hospitalmanagementsystem.models.PatientCase;
import com.tiyamike.hospitalmanagementsystem.services.DoctorService;
import com.tiyamike.hospitalmanagementsystem.services.PatientCaseService;
import com.tiyamike.hospitalmanagementsystem.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/PatientCases")
public class PatientCaseController {
    private final PatientCaseService patientCaseService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public PatientCaseController(PatientCaseService patientCaseService, PatientService patientService, DoctorService doctorService) {
        this.patientCaseService = patientCaseService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/All")
    public String getAllPatientCases(Model model){
        List<PatientCase> patientCaseList = patientCaseService.getPatientCases();
        model.addAttribute("patientCases", patientCaseList);
        return "patient_case/index";
    }

    @GetMapping("/Create")
    public String addPatientCase(PatientCase patientCase, Model model){
        List<Patient> patientList = patientService.getPatients();
        List<Doctor> doctorList = doctorService.getDoctors();
        model.addAttribute("patients", patientList);
        model.addAttribute("doctors", doctorList);
        return "patient_case/create";
    }

    @PostMapping("/Add")
    public String savePatientCase(@Valid PatientCase patientCase, Errors errors,
                                  RedirectAttributes redirectAttributes,
                                  Model model){
        if (errors.hasErrors()){
            List<Patient> patientList = patientService.getPatients();
            List<Doctor> doctorList = doctorService.getDoctors();
            model.addAttribute("patients", patientList);
            model.addAttribute("doctors", doctorList);
            return "patient_case/create";
        }
        patientCaseService.savePatientCase(patientCase);
        redirectAttributes.addFlashAttribute("message", "Patient Case was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/PatientCases/All";
    }

    @GetMapping("/Edit/{id}")
    public String editPatientCase(Model model, @PathVariable("id") long id){
        Optional<PatientCase> patientCase = patientCaseService.findById(id);
        List<Patient> patientList = patientService.getPatients();
        List<Doctor> doctorList = doctorService.getDoctors();
        model.addAttribute("patients", patientList);
        model.addAttribute("doctors", doctorList);
        model.addAttribute("patientCase", patientCase);
        return "patient_case/edit";
    }

    @PostMapping("/Update")
    public String updatePatientCase(@Valid PatientCase patientCase, Errors errors,
                                    RedirectAttributes redirectAttributes){
        if (errors.hasErrors()){
            return "patient_case/edit";
        }
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
