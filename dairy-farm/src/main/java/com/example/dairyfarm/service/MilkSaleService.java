package com.example.dairyfarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.MilkSale;
import com.example.dairyfarm.repository.MilkSaleRepository;

@Service
public class MilkSaleService {

    @Autowired
    private MilkSaleRepository milkSaleRepository;

    // Add Milk Sale
    public MilkSale saveMilkSale(MilkSale milkSale) {

        double quantity = milkSale.getQuantity() != null ? milkSale.getQuantity() : 0;
        double price = milkSale.getPricePerLiter() != null ? milkSale.getPricePerLiter() : 0;

        milkSale.setTotalAmount(quantity * price);

        return milkSaleRepository.save(milkSale);
    }

    // Get All Milk Sales
    public List<MilkSale> getAllMilkSales() {
        return milkSaleRepository.findAll();
    }

    // Get Milk Sale By Id
    public MilkSale getMilkSaleById(Long id) {

        Optional<MilkSale> optional = milkSaleRepository.findById(id);

        return optional.orElse(null);
    }

    // Update Milk Sale
    public MilkSale updateMilkSale(Long id, MilkSale milkSale) {

        MilkSale existingSale = getMilkSaleById(id);

        if (existingSale != null) {

            existingSale.setCustomer(milkSale.getCustomer());
            existingSale.setSaleDate(milkSale.getSaleDate());
            existingSale.setQuantity(milkSale.getQuantity());
            existingSale.setPricePerLiter(milkSale.getPricePerLiter());
            existingSale.setPaymentStatus(milkSale.getPaymentStatus());

            double quantity = milkSale.getQuantity() != null ? milkSale.getQuantity() : 0;
            double price = milkSale.getPricePerLiter() != null ? milkSale.getPricePerLiter() : 0;

            existingSale.setTotalAmount(quantity * price);

            return milkSaleRepository.save(existingSale);
        }

        return null;
    }

    // Delete Milk Sale
    public void deleteMilkSale(Long id) {
        milkSaleRepository.deleteById(id);
    }

}