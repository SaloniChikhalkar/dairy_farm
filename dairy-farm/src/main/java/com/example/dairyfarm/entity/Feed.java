package com.example.dairyfarm.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "feed")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long feedId;

    @Column(name = "feed_name")
    private String feedName;

    @Column(name = "feed_type")
    private String feedType;

    private Double quantity;

    private String unit;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    private String supplier;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    private String status;

    public Feed() {
    }

    public Feed(Long feedId, String feedName, String feedType,
            Double quantity, String unit, Double purchasePrice,
            String supplier, LocalDate purchaseDate,
            LocalDate expiryDate, String status) {

        this.feedId = feedId;
        this.feedName = feedName;
        this.feedType = feedType;
        this.quantity = quantity;
        this.unit = unit;
        this.purchasePrice = purchasePrice;
        this.supplier = supplier;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
