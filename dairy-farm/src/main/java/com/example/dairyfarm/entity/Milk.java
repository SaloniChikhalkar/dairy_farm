package com.example.dairyfarm.entity;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "milk_collection")
public class Milk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milk_id")
    private Long milkId;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Column(name = "collection_date")
    private LocalDate collectionDate;

    @Column(name = "morning_qty")
    private Double morningQty;

    @Column(name = "evening_qty")
    private Double eveningQty;

    @Column(name = "total_qty")
    private Double totalQty;

    // Default Constructor
    public Milk() {
    }

    // Parameterized Constructor
    public Milk(Long milkId, Animal animal, LocalDate collectionDate,
                          Double morningQty, Double eveningQty, Double totalQty) {
        this.milkId = milkId;
        this.animal = animal;
        this.collectionDate = collectionDate;
        this.morningQty = morningQty;
        this.eveningQty = eveningQty;
        this.totalQty = totalQty;
    }

    public Long getMilkId() {
        return milkId;
    }

    public void setMilkId(Long milkId) {
        this.milkId = milkId;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public Double getMorningQty() {
        return morningQty;
    }

    public void setMorningQty(Double morningQty) {
        this.morningQty = morningQty;
    }

    public Double getEveningQty() {
        return eveningQty;
    }

    public void setEveningQty(Double eveningQty) {
        this.eveningQty = eveningQty;
    }

    public Double getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Double totalQty) {
        this.totalQty = totalQty;
    }
}
