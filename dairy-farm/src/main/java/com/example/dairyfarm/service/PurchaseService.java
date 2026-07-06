package com.example.dairyfarm.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.Purchase;
import com.example.dairyfarm.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    // Save Purchase
    public Purchase savePurchase(Purchase purchase) {

        purchase.setTotalAmount(
                purchase.getQuantity() * purchase.getUnitPrice());

        return purchaseRepository.save(purchase);
    }

    // Get All
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    // Get By Id
    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    // Update
    public Purchase updatePurchase(Long id, Purchase purchase) {

        Purchase existing = purchaseRepository.findById(id).orElse(null);

        if (existing != null) {

            existing.setPurchaseDate(purchase.getPurchaseDate());
            existing.setItemName(purchase.getItemName());
            existing.setCategory(purchase.getCategory());
            existing.setQuantity(purchase.getQuantity());
            existing.setUnitPrice(purchase.getUnitPrice());

            existing.setTotalAmount(
                    purchase.getQuantity() * purchase.getUnitPrice());

            existing.setPaymentStatus(purchase.getPaymentStatus());

            return purchaseRepository.save(existing);
        }

        return null;
    }

    // Delete
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

    // Search Item
    public List<Purchase> searchItem(String itemName) {
        return purchaseRepository.findByItemNameContainingIgnoreCase(itemName);
    }

    // Filter Date
    public List<Purchase> filterDate(LocalDate date) {
        return purchaseRepository.findByPurchaseDate(date);
    }

}
