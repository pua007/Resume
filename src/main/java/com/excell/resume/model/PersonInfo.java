package com.excell.resume.model;

import java.awt.Image;
import java.util.Arrays;

public class PersonInfo {
    byte[] photo;
    String name;
    String email;
    String address;
    String phoneNumber;
    String birthDate;

    public PersonInfo() {
    }

    public PersonInfo(byte[] photo, String name, String email, String address, String phoneNumber,
        String birthDate) {
        this.photo = photo;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
            "photo=" + Arrays.toString(photo) +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", birthDate='" + birthDate + '\'' +
            '}';
    }
}
