package com.example.dairyfarm.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dairyfarm.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByItemNameContainingIgnoreCase(String itemName);

    List<Purchase> findByPurchaseDate(LocalDate purchaseDate);

}
