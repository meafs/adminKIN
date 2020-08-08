package com.sust.adminkinblood;

public class Dnr_Healper {
    private String fullName, phoneNumber,address, gender, bloodGroup, occupation, institute, donorStatus, donatedBefore, email, currentAddress, UID;
    private int bDay, bMonth, bYear, donateTimes, dDay, dMonth, dYear;


    public Dnr_Healper() {
    }
    public Dnr_Healper(String fullName, String phoneNumber, String address, String gender, String bloodGroup, String occupation,
                       String institute, String donorStatus, String donatedBefore, String email ,String currentAddress,
                       int bDay, int bMonth, int bYear, int donateTimes, int dDay, int dMonth, int dYear, String UID) {

        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.occupation = occupation;
        this.institute = institute;
        this.donorStatus = donorStatus;
        this.donatedBefore = donatedBefore;
        this.bDay = bDay;
        this.bMonth = bMonth;
        this.bYear = bYear;
        this.donateTimes = donateTimes;
        this.dDay = dDay;
        this.dMonth = dMonth;
        this.dYear = dYear;
        this.email = email;
        this.currentAddress = currentAddress;
        this.UID = UID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDonatedBefore() {
        return donatedBefore;
    }

    public void setDonatedBefore(String donatedBefore) {
        this.donatedBefore = donatedBefore;
    }

    public int getbDay() {
        return bDay;
    }

    public void setbDay(int bDay) {
        this.bDay = bDay;
    }

    public int getbMonth() {
        return bMonth;
    }

    public void setbMonth(int bMonth) {
        this.bMonth = bMonth;
    }

    public int getbYear() {
        return bYear;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
    }

    public int getDonateTimes() {
        return donateTimes;
    }

    public void setDonateTimes(int donateTimes) {
        this.donateTimes = donateTimes;
    }

    public int getdDay() {
        return dDay;
    }

    public void setdDay(int dDay) {
        this.dDay = dDay;
    }

    public int getdMonth() {
        return dMonth;
    }

    public void setdMonth(int dMonth) {
        this.dMonth = dMonth;
    }

    public int getdYear() {
        return dYear;
    }

    public void setdYear(int dYear) {
        this.dYear = dYear;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
