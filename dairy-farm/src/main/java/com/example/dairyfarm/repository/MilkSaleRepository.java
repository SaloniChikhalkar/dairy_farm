package com.example.dairyfarm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dairyfarm.entity.MilkSale;

@Repository
public interface MilkSaleRepository extends JpaRepository<MilkSale, Long> {

}