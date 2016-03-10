package com.dstvdm.payu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URL;
import java.util.List;

/**
 * Created by paul on 2016/03/10.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private URL notifyUrl;
    private String customerIp;
    private int merchantPosId;
    private String description;
    private String currencyCode;
    private int totalAmount;
    private List<Products> products;

    public URL getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(URL notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public int getMerchantPosId() {
        return merchantPosId;
    }

    public void setMerchantPosId(int merchantPosId) {
        this.merchantPosId = merchantPosId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
