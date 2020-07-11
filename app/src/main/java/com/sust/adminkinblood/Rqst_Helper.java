package com.sust.adminkinblood;

public class Rqst_Helper {
    private String hospital_, condition_, noOfBags_, blood_group, fullName, phoneNumber ,uid;

    public Rqst_Helper(String hospital_, String condition_, String noOfBags_, String blood_group,String uid, String fullName, String phoneNumber) {
        this.hospital_ = hospital_;
        this.condition_ = condition_;
        this.noOfBags_ = noOfBags_;
        this.blood_group = blood_group;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.uid = uid ;
    }

    public Rqst_Helper() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHospital_() {
        return hospital_;
    }

    public void setHospital_(String hospital_) {
        this.hospital_ = hospital_;
    }

    public String getCondition_() {
        return condition_;
    }

    public void setCondition_(String condition_) {
        this.condition_ = condition_;
    }

    public String getNoOfBags_() {
        return noOfBags_;
    }

    public void setNoOfBags_(String noOfBags_) {
        this.noOfBags_ = noOfBags_;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
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
