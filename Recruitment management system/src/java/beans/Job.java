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
public class Job {

    private String company;
    private String address;
    private String email;
    private String contact;
    private String location;
    private String specification;
    private String experience;
    private String skills;

    public Job() {
    }

    public Job(String company, String address, String email, String contact, String location, String specification, String experience, String skills) {
        this.company = company;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.location = location;
        this.specification = specification;
        this.experience = experience;
        this.skills = skills;
    }

    public Job(String specification, String experience, String skills) {
        this.specification = specification;
        this.experience = experience;
        this.skills = skills;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
