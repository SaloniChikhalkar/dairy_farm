package com.example.dairyfarm.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dairyfarm.entity.Milk;

@Repository
public interface MilkRepository extends JpaRepository<Milk, Long> {

    // Find milk collection by date
    List<Milk> findByCollectionDate(LocalDate collectionDate);

    // Find all milk records of a particular animal
    List<Milk> findByAnimalAnimalId(Long animalId);

    // Find milk records between two dates
    List<Milk> findByCollectionDateBetween(LocalDate startDate, LocalDate endDate);

}
