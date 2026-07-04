package com.example.dairyfarm.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;

    @Column(nullable = false, unique = true)
    private String tagNumber;

    private String animalType;

    private String breed;

    private String gender;

    private LocalDate birthDate;

    private LocalDate purchaseDate;

    private Double purchasePrice;

    private Double weight;

    private String healthStatus;

    private String status;

    public Animal() {
    }

    public Animal(Long animalId, String tagNumber, String animalType, String breed,
                  String gender, LocalDate birthDate, LocalDate purchaseDate,
                  Double purchasePrice, Double weight,
                  String healthStatus, String status) {

        this.animalId = animalId;
        this.tagNumber = tagNumber;
        this.animalType = animalType;
        this.breed = breed;
        this.gender = gender;
        this.birthDate = birthDate;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.weight = weight;
        this.healthStatus = healthStatus;
        this.status = status;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
