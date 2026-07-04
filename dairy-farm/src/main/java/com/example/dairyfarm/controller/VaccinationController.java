package com.example.dairyfarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dairyfarm.entity.Vaccination;
import com.example.dairyfarm.service.VaccinationService;

@RestController
@RequestMapping("/api/vaccinations")
@CrossOrigin(origins = "*")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    // Add Vaccination
    @PostMapping
    public Vaccination saveVaccination(@RequestBody Vaccination vaccination) {
        return vaccinationService.saveVaccination(vaccination);
    }

    // Get All
    @GetMapping
    public List<Vaccination> getAllVaccinations() {
        return vaccinationService.getAllVaccinations();
    }

    // Get By ID
    @GetMapping("/{id}")
    public Vaccination getVaccination(@PathVariable Long id) {
        return vaccinationService.getVaccinationById(id);
    }

    // Update
    @PutMapping("/{id}")
    public Vaccination updateVaccination(@PathVariable Long id,
            @RequestBody Vaccination vaccination) {

        return vaccinationService.updateVaccination(id, vaccination);
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteVaccination(@PathVariable Long id) {

        vaccinationService.deleteVaccination(id);

        return "Vaccination Deleted Successfully";
    }

}
