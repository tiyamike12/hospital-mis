package com.tiyamike.hospitalmanagementsystem.controllers;

import com.tiyamike.hospitalmanagementsystem.models.Department;
import com.tiyamike.hospitalmanagementsystem.models.LabResult;
import com.tiyamike.hospitalmanagementsystem.models.Patient;
import com.tiyamike.hospitalmanagementsystem.services.LabResultService;
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
@RequestMapping("/LabResults")
public class LabResultController {
    private final LabResultService labResultService;
    private final PatientService patientService;

    public LabResultController(LabResultService labResultService, PatientService patientService) {
        this.labResultService = labResultService;
        this.patientService = patientService;
    }

    @GetMapping("/All")
    public String getAllLabResults(Model model){
        List<LabResult> labResultList = labResultService.getLabResults();
        model.addAttribute("labResults", labResultList);
        return "lab_result/index";
    }

    @GetMapping("/Create")
    public String addLabResult(LabResult labResult, Model model){
        List<Patient> patientList = patientService.getPatients();
        model.addAttribute("patients", patientList);
        return "lab_result/create";
    }

    @PostMapping("/Add")
    public String saveLabResult(@Valid LabResult labResult, Errors errors,
                                RedirectAttributes redirectAttributes,
                                Model model){
        if (errors.hasErrors()){
            List<Patient> patientList = patientService.getPatients();
            model.addAttribute("patients", patientList);
            return "lab_result/create";
        }
        labResultService.saveLabResult(labResult);
        redirectAttributes.addFlashAttribute("message", "Lab Result was created successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/LabResults/All";
    }

    @GetMapping("/Edit/{id}")
    public String editLabResult(Model model, @PathVariable("id") long id){
        Optional<LabResult> labResult = labResultService.findById(id);
        List<Patient> patientList = patientService.getPatients();
        model.addAttribute("patients", patientList);
        model.addAttribute("labResult", labResult);
        return "lab_result/edit";
    }

    @PostMapping("/Update")
    public String updateLabResult(@Valid LabResult labResult, Errors errors,
                                  RedirectAttributes redirectAttributes){
        if (errors.hasErrors()){
            return "lab_result/edit";
        }
        labResultService.updateLabResult(labResult);
        redirectAttributes.addFlashAttribute("message", "Lab Result was updated successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/LabResults/All";
    }

    @RequestMapping(value = "/Delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteLabResult(@PathVariable long id, RedirectAttributes redirectAttributes){
        labResultService.deleteLabResult(id);
        redirectAttributes.addFlashAttribute("message", "Lab Result was removed successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/LabResults/All";
    }
}
