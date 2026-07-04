package com.example.dairyfarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dairyfarm.entity.MilkSale;
import com.example.dairyfarm.service.MilkSaleService;

@RestController
@RequestMapping("/api/milk-sales")
@CrossOrigin(origins = "*")
public class MilkSaleController {

    @Autowired
    private MilkSaleService milkSaleService;

    @GetMapping
    public List<MilkSale> getAllMilkSales() {
        return milkSaleService.getAllMilkSales();
    }

    @GetMapping("/{id}")
    public MilkSale getMilkSaleById(@PathVariable Long id) {
        return milkSaleService.getMilkSaleById(id);
    }

    @PostMapping
    public MilkSale addMilkSale(@RequestBody MilkSale milkSale) {
        return milkSaleService.saveMilkSale(milkSale);
    }

    @PutMapping("/{id}")
    public MilkSale updateMilkSale(@PathVariable Long id,
                                   @RequestBody MilkSale milkSale) {
        return milkSaleService.updateMilkSale(id, milkSale);
    }

    @DeleteMapping("/{id}")
    public String deleteMilkSale(@PathVariable Long id) {

        milkSaleService.deleteMilkSale(id);

        return "Milk Sale Deleted Successfully";
    }
}
