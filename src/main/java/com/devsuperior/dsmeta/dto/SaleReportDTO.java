package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleReportDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SaleReportDTO(Sale entity) {
        id = entity.getId();
        date = entity.getDate();
        amount = entity.getAmount();
        sellerName = entity.getSeller().getName();
    }

    // attributes
    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String toString() {
        return "SaleReportDTO [id = " + id
                + ", date = " + date
                + ", amount = " + amount
                + ", sellerName = "
                + sellerName + "]\n";
    }

}
