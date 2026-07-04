package com.example.dairyfarm.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.Vaccination;
import com.example.dairyfarm.repository.VaccinationRepository;

@Service
public class VaccinationService {

    @Autowired
    private VaccinationRepository vaccinationRepository;

    // Save
    public Vaccination saveVaccination(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    // Get All
    public List<Vaccination> getAllVaccinations() {
        return vaccinationRepository.findAll();
    }

    // Get By ID
    public Vaccination getVaccinationById(Long id) {
        return vaccinationRepository.findById(id).orElse(null);
    }

    // Update
    public Vaccination updateVaccination(Long id, Vaccination vaccination) {

        Vaccination existing = getVaccinationById(id);

        if (existing != null) {

            existing.setAnimal(vaccination.getAnimal());
            existing.setVaccineName(vaccination.getVaccineName());
            existing.setVaccinationDate(vaccination.getVaccinationDate());
            existing.setNextDueDate(vaccination.getNextDueDate());
            existing.setVeterinarian(vaccination.getVeterinarian());
            existing.setRemarks(vaccination.getRemarks());

            return vaccinationRepository.save(existing);
        }

        return null;
    }

    // Delete
    public void deleteVaccination(Long id) {
        vaccinationRepository.deleteById(id);
    }

}
