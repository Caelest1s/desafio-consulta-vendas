package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerDTO {

    // attributes
    Long id;
    String name;
    String email;
    String phone;

    // constructor
    public SellerDTO() {}

    public SellerDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public SellerDTO(Seller entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
    }

    // getter and setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    // toString
    @Override
    public String toString() {
        return "SellerDTO \t[ID = " + id + ", "
                + "NAME = " + name + ", "
                + "EMAIL = " + email + ", "
                + "PHONE = " + phone + "]";
    }

}
