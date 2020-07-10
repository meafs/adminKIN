package com.sust.adminkinblood;

public class Dnr_Healper {
    private String fullName, phoneNumber,address, gender, bloodGroup, occupation, institute, donarstatus, donatedBefore;
    private int bDay, bMonth, bYear, donate_times, dDay, dMonth, dYear;

    public Dnr_Healper() {
    }
    public Dnr_Healper(String fullName, String phoneNumber, String address, String gender, String bloodGroup, String occupation, String institute, String donarstatus, String donatedBefore, int bDay, int bMonth, int bYear, int donate_times, int dDay, int dMonth, int dYear) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.occupation = occupation;
        this.institute = institute;
        this.donarstatus = donarstatus;
        this.donatedBefore = donatedBefore;
        this.bDay = bDay;
        this.bMonth = bMonth;
        this.bYear = bYear;
        this.donate_times = donate_times;
        this.dDay = dDay;
        this.dMonth = dMonth;
        this.dYear = dYear;
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

    public String getDonarstatus() {
        return donarstatus;
    }

    public void setDonarstatus(String donarstatus) {
        this.donarstatus = donarstatus;
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

    public int getDonate_times() {
        return donate_times;
    }

    public void setDonate_times(int donate_times) {
        this.donate_times = donate_times;
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
}
