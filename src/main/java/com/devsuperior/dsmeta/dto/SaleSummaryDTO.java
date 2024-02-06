package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public class SaleSummaryDTO {

    // attributes
    private String name;
    private Double amount;

    // constructor
    public SaleSummaryDTO(SaleSummaryProjection projection){
        name = projection.getName();
        amount = projection.getTotal();
    }

    // getter and setter
    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "SaleSummaryDTO [name=" + name + ", amount=" + amount + "]";
    }

}
