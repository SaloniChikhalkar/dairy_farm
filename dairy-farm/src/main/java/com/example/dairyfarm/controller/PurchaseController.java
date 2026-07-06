package com.example.dairyfarm.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dairyfarm.entity.Purchase;
import com.example.dairyfarm.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin("*")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // Add Purchase
    @PostMapping
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }

    // Get All
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    // Get By ID
    @GetMapping("/{id}")
    public Purchase getPurchase(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    // Update
    @PutMapping("/{id}")
    public Purchase updatePurchase(@PathVariable Long id,
                                   @RequestBody Purchase purchase) {

        return purchaseService.updatePurchase(id, purchase);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
    }

    // Search Item
    @GetMapping("/search")
    public List<Purchase> searchItem(@RequestParam String itemName) {
        return purchaseService.searchItem(itemName);
    }

    // Filter Date
    @GetMapping("/date")
    public List<Purchase> filterDate(@RequestParam LocalDate date) {
        return purchaseService.filterDate(date);
    }

}
