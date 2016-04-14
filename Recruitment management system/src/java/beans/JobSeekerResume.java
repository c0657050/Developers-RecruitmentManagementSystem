/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author sahil
 */
public class JobSeekerResume {

    private String position;
    private String preferredLocation;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    private String areaOfInterest;
    private String primarySkills;
    private int experience;
    private String currentLocation;
    private String highestQualification;
    private int yearOfPassing;

    public JobSeekerResume(String position, String preferredLocation, String firstName, String lastName, String gender, String phone, String email, String areaOfInterest, String primarySkills, int experience, String currentLocation, String highestQualification, int yearOfPassing) {
        this.position = position;
        this.preferredLocation = preferredLocation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.areaOfInterest = areaOfInterest;
        this.primarySkills = primarySkills;
        this.experience = experience;
        this.currentLocation = currentLocation;
        this.highestQualification = highestQualification;
        this.yearOfPassing = yearOfPassing;
    }

    public JobSeekerResume() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(String areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    public String getPrimarySkills() {
        return primarySkills;
    }

    public void setPrimarySkills(String primarySkills) {
        this.primarySkills = primarySkills;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public int getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(int yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

}
