package com.example.dairyfarm.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "milk_sales")
public class MilkSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    private Double quantity;

    @Column(name = "price_per_liter")
    private Double pricePerLiter;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "payment_status")
    private String paymentStatus;

    public MilkSale() {
    }

    public MilkSale(Long saleId, Customer customer, LocalDate saleDate,
                    Double quantity, Double pricePerLiter,
                    Double totalAmount, String paymentStatus) {
        this.saleId = saleId;
        this.customer = customer;
        this.saleDate = saleDate;
        this.quantity = quantity;
        this.pricePerLiter = pricePerLiter;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(Double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
