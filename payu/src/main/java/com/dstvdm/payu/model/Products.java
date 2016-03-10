package com.dstvdm.payu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by paul on 2016/03/10.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {

    private String name;
    private String unitPrice;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
