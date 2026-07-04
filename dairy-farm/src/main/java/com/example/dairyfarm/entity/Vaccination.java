package com.example.dairyfarm.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "vaccinations")
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_id")
    private Long vaccinationId;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;

    @Column(name = "next_due_date")
    private LocalDate nextDueDate;

    private String veterinarian;

    private String remarks;

    public Vaccination() {
    }

    public Vaccination(Long vaccinationId, Animal animal, String vaccineName,
            LocalDate vaccinationDate, LocalDate nextDueDate,
            String veterinarian, String remarks) {

        this.vaccinationId = vaccinationId;
        this.animal = animal;
        this.vaccineName = vaccineName;
        this.vaccinationDate = vaccinationDate;
        this.nextDueDate = nextDueDate;
        this.veterinarian = veterinarian;
        this.remarks = remarks;
    }

    public Long getVaccinationId() {
        return vaccinationId;
    }

    public void setVaccinationId(Long vaccinationId) {
        this.vaccinationId = vaccinationId;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}