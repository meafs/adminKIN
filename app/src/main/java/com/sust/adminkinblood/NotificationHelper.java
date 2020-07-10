package com.sust.adminkinblood;

public class NotificationHelper {

    private String bloodGroup, hospital, condition, noOfBags, uid, fullName, phoneNumber;

    public NotificationHelper() {
    }

    public NotificationHelper(String bloodGroup, String hospital, String condition, String noOfBags, String uid, String fullName, String phoneNumber) {
        this.bloodGroup = bloodGroup;
        this.hospital = hospital;
        this.condition = condition;
        this.noOfBags = noOfBags;
        this.uid = uid;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}
