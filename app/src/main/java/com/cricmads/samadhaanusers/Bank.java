package com.cricmads.samadhaanusers;

public class Bank {
    private String udhyogAdhaarno, amount, lastUpdated, created, locked, penalty;

    public Bank() {
    }

    public Bank(String udhyogAdhaarno, String amount, String lastUpdated, String created, String locked, String penalty) {
        this.udhyogAdhaarno = udhyogAdhaarno;
        this.amount = amount;
        this.lastUpdated = lastUpdated;
        this.created = created;
        this.locked = locked;
        this.penalty = penalty;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getUdhyogAdhaarno() {
        return udhyogAdhaarno;
    }

    public void setUdhyogAdhaarno(String udhyogAdhaarno) {
        this.udhyogAdhaarno = udhyogAdhaarno;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
