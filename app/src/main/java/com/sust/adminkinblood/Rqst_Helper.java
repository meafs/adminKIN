package com.sust.adminkinblood;

import java.util.Map;

public class Rqst_Helper {



    private String donorHaveToGoLocationAddress, donorHaveToGoLocationName, condition, noOfBags, bloodGroup, fullName, phoneNumber, uid, text, time;
    private double donorHaveToGoLatitude, donorHaveToGoLongitude;

    public Rqst_Helper(){

    }

    public Rqst_Helper(String donorHaveToGoLocationAddress, String donorHaveToGoLocationName, String condition, String noOfBags, String bloodGroup, String uid, String fullName, String phoneNumber, double donorHaveToGoLatitude, double donorHaveToGoLongitude, String text, String time) {
        this.donorHaveToGoLocationAddress = donorHaveToGoLocationAddress;
        this.donorHaveToGoLocationName = donorHaveToGoLocationName;
        this.condition = condition;
        this.noOfBags = noOfBags;
        this.bloodGroup = bloodGroup;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.uid = uid ;
        this.donorHaveToGoLatitude = donorHaveToGoLatitude;
        this.donorHaveToGoLongitude = donorHaveToGoLongitude;
        this.text = text;
        this.time = time;
    }

    public String getDonorHaveToGoLocationAddress() {
        return donorHaveToGoLocationAddress;
    }

    public void setDonorHaveToGoLocationAddress(String donorHaveToGoLocationAddress) {
        this.donorHaveToGoLocationAddress = donorHaveToGoLocationAddress;
    }

    public String getDonorHaveToGoLocationName() {
        return donorHaveToGoLocationName;
    }

    public void setDonorHaveToGoLocationName(String donorHaveToGoLocationName) {
        this.donorHaveToGoLocationName = donorHaveToGoLocationName;
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

    public double getDonorHaveToGoLatitude() {
        return donorHaveToGoLatitude;
    }

    public void setDonorHaveToGoLatitude(double donorHaveToGoLatitude) {
        this.donorHaveToGoLatitude = donorHaveToGoLatitude;
    }

    public double getDonorHaveToGoLongitude() {
        return donorHaveToGoLongitude;
    }

    public void setDonorHaveToGoLongitude(double donorHaveToGoLongitude) {
        this.donorHaveToGoLongitude = donorHaveToGoLongitude;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
