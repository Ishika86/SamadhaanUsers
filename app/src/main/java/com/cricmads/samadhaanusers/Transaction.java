package com.cricmads.samadhaanusers;

public class Transaction {
    private String date, time, amount, manufacturerUserName, id, status, supplierUserName;

    public Transaction() {
    }

    public Transaction(String date, String time, String amount, String manufacturerUserName, String id, String status,String supplierUserName) {
        this.date = date;
        this.supplierUserName = supplierUserName;
        this.time = time;
        this.amount = amount;
        this.manufacturerUserName = manufacturerUserName;
        this.id = id;
        this.status = status;
    }

    public String getSupplierUserName() {
        return supplierUserName;
    }

    public void setSupplierUserName(String supplierUserName) {
        this.supplierUserName = supplierUserName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getManufacturerUserName() {
        return manufacturerUserName;
    }

    public void setManufacturerUserName(String manufacturerUserName) {
        this.manufacturerUserName = manufacturerUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
