package com.sust.adminkinblood;

public class Rqst_Helper {



    private String donorHaveToGoLocationAddress, condition, noOfBags, bloodGroup, fullName, phoneNumber, uid;

    public Rqst_Helper(){

    }

    public Rqst_Helper(String donorHaveToGoLocationAddress, String condition, String noOfBags, String bloodGroup, String uid, String fullName, String phoneNumber) {
        this.donorHaveToGoLocationAddress = donorHaveToGoLocationAddress;
        this.condition = condition;
        this.noOfBags = noOfBags;
        this.bloodGroup = bloodGroup;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.uid = uid ;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getNoOfBags() {
        return noOfBags;
    }

    public void setNoOfBags(String noOfBags) {
        this.noOfBags = noOfBags;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDonorHaveToGoLocationAddress() {
        return donorHaveToGoLocationAddress;
    }

    public void setDonorHaveToGoLocationAddress(String donorHaveToGoLocationAddress) {
        this.donorHaveToGoLocationAddress = donorHaveToGoLocationAddress;
    }


}
