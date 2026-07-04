package com.example.dairyfarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dairyfarm.entity.Milk;
import com.example.dairyfarm.service.MilkService;

@RestController
@RequestMapping("/api/milk")
@CrossOrigin(origins = "*")
public class MilkController {

    @Autowired
    private MilkService milkService;

    // ===================== GET ALL MILK RECORDS =====================
    @GetMapping
    public List<Milk> getAllMilk() {
        return milkService.getAllMilk();
    }

    // ===================== GET MILK BY ID =====================
    @GetMapping("/{id}")
    public Milk getMilkById(@PathVariable Long id) {
        return milkService.getMilkById(id);
    }

    // ===================== ADD MILK RECORD =====================
    @PostMapping
    public Milk addMilk(@RequestBody Milk milk) {
        return milkService.saveMilk(milk);
    }

    // ===================== UPDATE MILK RECORD =====================
    @PutMapping("/{id}")
    public Milk updateMilk(@PathVariable Long id,
                           @RequestBody Milk milk) {
        return milkService.updateMilk(id, milk);
    }

    // ===================== DELETE MILK RECORD =====================
    @DeleteMapping("/{id}")
    public String deleteMilk(@PathVariable Long id) {

        milkService.deleteMilk(id);

        return "Milk Record Deleted Successfully";
    }

}
