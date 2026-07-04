package com.example.dairyfarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.Milk;
import com.example.dairyfarm.repository.MilkRepository;

@Service
public class MilkService {

    @Autowired
    private MilkRepository milkRepository;

    // Add Milk Record
    public Milk saveMilk(Milk milk) {

        double morning = milk.getMorningQty() != null ? milk.getMorningQty() : 0;
        double evening = milk.getEveningQty() != null ? milk.getEveningQty() : 0;

        milk.setTotalQty(morning + evening);

        return milkRepository.save(milk);
    }

    // Get All Milk Records
    public List<Milk> getAllMilk() {
        return milkRepository.findAll();
    }

    // Get Milk By ID
    public Milk getMilkById(Long id) {

        Optional<Milk> optional = milkRepository.findById(id);

        return optional.orElse(null);
    }

    // Update Milk Record
    public Milk updateMilk(Long id, Milk milk) {

        Milk existingMilk = getMilkById(id);

        if (existingMilk != null) {

            existingMilk.setAnimal(milk.getAnimal());
            existingMilk.setCollectionDate(milk.getCollectionDate());
            existingMilk.setMorningQty(milk.getMorningQty());
            existingMilk.setEveningQty(milk.getEveningQty());

            double morning = milk.getMorningQty() != null ? milk.getMorningQty() : 0;
            double evening = milk.getEveningQty() != null ? milk.getEveningQty() : 0;

            existingMilk.setTotalQty(morning + evening);

            return milkRepository.save(existingMilk);
        }

        return null;
    }

    // Delete Milk Record
    public void deleteMilk(Long id) {
        milkRepository.deleteById(id);
    }
}
