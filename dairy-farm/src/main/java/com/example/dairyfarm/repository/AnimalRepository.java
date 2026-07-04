package com.example.dairyfarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dairyfarm.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Find animal by tag number
    Animal findByTagNumber(String tagNumber);

}
