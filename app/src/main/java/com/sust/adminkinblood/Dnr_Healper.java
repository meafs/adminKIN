package com.sust.adminkinblood;

import java.util.Date;

public class Dnr_Healper {
    private String fullName, phoneNumber, currentLocationName, currentLocationAddress,  gender, bloodGroup, occupation, institute, donorStatus, email, uid, department, formFactor, homeDistrict, requestStatus, session;
    private int donateTimes, penalty;
    private Double currentLocationLatitude, currentLocationLongitude;
    private boolean available, donating;



    public Dnr_Healper() {
    }

    public Dnr_Healper(String fullName, String phoneNumber, String currentLocationName, String currentLocationAddress, String gender, String bloodGroup, String occupation, String institute, String donorStatus, String email, String department, String formFactor, String homeDistrict, String requestStatus, String session, String uid, int donateTimes, int penalty, Date bDate, Date lDonation, Double currentLocationLatitude, Double currentLocationLongitude, boolean available, boolean donating) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.currentLocationName = currentLocationName;
        this.currentLocationAddress = currentLocationAddress;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.occupation = occupation;
        this.institute = institute;
        this.donorStatus = donorStatus;
        this.email = email;
        this.uid = uid;
        this.department = department;
        this.formFactor = formFactor;
        this.homeDistrict = homeDistrict;
        this.requestStatus = requestStatus;
        this.session = session;
        this.uid = uid;
        this.donateTimes = donateTimes;
        this.penalty = penalty;
        this.currentLocationLatitude = currentLocationLatitude;
        this.currentLocationLongitude = currentLocationLongitude;
        this.available = available;
        this.donating = donating;
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

    public String getCurrentLocationName() {
        return currentLocationName;
    }

    public void setCurrentLocationName(String currentLocationName) {
        this.currentLocationName = currentLocationName;
    }

    public String getCurrentLocationAddress() {
        return currentLocationAddress;
    }

    public void setCurrentLocationAddress(String currentLocationAddress) {
        this.currentLocationAddress = currentLocationAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDonorStatus() {
        return donorStatus;
    }

    public void setDonorStatus(String donorStatus) {
        this.donorStatus = donorStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getHomeDistrict() {
        return homeDistrict;
    }

    public void setHomeDistrict(String homeDistrict) {
        this.homeDistrict = homeDistrict;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getDonateTimes() {
        return donateTimes;
    }

    public void setDonateTimes(int donateTimes) {
        this.donateTimes = donateTimes;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public Double getCurrentLocationLatitude() {
        return currentLocationLatitude;
    }

    public void setCurrentLocationLatitude(Double currentLocationLatitude) {
        this.currentLocationLatitude = currentLocationLatitude;
    }

    public Double getCurrentLocationLongitude() {
        return currentLocationLongitude;
    }

    public void setCurrentLocationLongitude(Double currentLocationLongitude) {
        this.currentLocationLongitude = currentLocationLongitude;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isDonating() {
        return donating;
    }

    public void setDonating(boolean donating) {
        this.donating = donating;
    }
}
