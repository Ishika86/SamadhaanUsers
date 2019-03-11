package com.cricmads.samadhaanusers;

public class UserData {

    private String typeOfAccount, name, email, udhyogAadhaar, aadhaar, udyogName, address, pincode, gender, phone, socialCategory,physicallyHandicaped, typeOfOrganisation, panNumber, GSTIN, district, state, bankAccountNumber, ifsc, businessActivity, noOfEmployess, UID;

    public UserData() {
    }

    public UserData(String typeOfAccount, String name, String email, String UID, String udhyogAadhaar, String aadhaar, String udyogName, String address, String pincode, String gender, String phone, String socialCategory, String physicallyHandicaped, String typeOfOrganisation, String panNumber, String GSTIN, String district, String state, String bankAccountNumber, String ifsc, String businessActivity, String noOfEmployess) {

        this.typeOfAccount = typeOfAccount;
        this.name = name;
        this.email = email;
        this.UID = UID;
        this.udhyogAadhaar = udhyogAadhaar;
        this.aadhaar = aadhaar;
        this.udyogName = udyogName;
        this.address = address;
        this.pincode = pincode;
        this.gender = gender;
        this.phone = phone;
        this.socialCategory = socialCategory;
        this.physicallyHandicaped = physicallyHandicaped;
        this.typeOfOrganisation = typeOfOrganisation;
        this.panNumber = panNumber;
        this.GSTIN = GSTIN;
        this.district = district;
        this.state = state;
        this.bankAccountNumber = bankAccountNumber;
        this.ifsc = ifsc;
        this.businessActivity = businessActivity;
        this.noOfEmployess = noOfEmployess;
    }


    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUdhyogAadhaar() {
        return udhyogAadhaar;
    }

    public void setUdhyogAadhaar(String udhyogAadhaar) {
        this.udhyogAadhaar = udhyogAadhaar;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getUdyogName() {
        return udyogName;
    }

    public void setUdyogName(String udyogName) {
        this.udyogName = udyogName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSocialCategory() {
        return socialCategory;
    }

    public void setSocialCategory(String socialCategory) {
        this.socialCategory = socialCategory;
    }

    public String getTypeOfOrganisation() {
        return typeOfOrganisation;
    }

    public void setTypeOfOrganisation(String typeOfOrganisation) {
        this.typeOfOrganisation = typeOfOrganisation;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBusinessActivity() {
        return businessActivity;
    }

    public void setBusinessActivity(String businessActivity) {
        this.businessActivity = businessActivity;
    }

    public String getNoOfEmployess() {
        return noOfEmployess;
    }

    public void setNoOfEmployess(String noOfEmployess) {
        this.noOfEmployess = noOfEmployess;
    }

    public String getPhysicallyHandicaped() {
        return physicallyHandicaped;
    }

    public void setPhysicallyHandicaped(String physicallyHandicaped) {
        this.physicallyHandicaped = physicallyHandicaped;
    }
}
