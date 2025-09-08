package com.example.onlineStore.model;

import java.math.BigDecimal;
import java.util.Date;

public class Discount {
    private String idDiscount;
    private String nameDiscount;
    private BigDecimal percentage; // 0.10 = 10%
    private Date startDate;
    private Date endDate;

    // Constructor
    public Discount(String idDiscount, String nameDiscount, BigDecimal percentage, Date startDate, Date endDate) {
        this.idDiscount = idDiscount;
        this.nameDiscount = nameDiscount;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters y Setters
    public String getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(String idDiscount) {
        this.idDiscount = idDiscount;
    }

    public String getNameDiscount() {
        return nameDiscount;
    }

    public void setNameDiscount(String nameDiscount) {
        this.nameDiscount = nameDiscount;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Función para verificar si el descuento está activo
    public boolean isActive() {
        Date now = new Date();
        return now.after(startDate) && now.before(endDate);
    }
}
