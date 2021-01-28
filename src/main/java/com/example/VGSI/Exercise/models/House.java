package com.example.VGSI.Exercise.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class House {
    @JsonIgnore
    private String houseId;
    private String ownerfirstName;
    private String ownerlastName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String propertyType;
    private String location;

    public House() {}

    public House(String houseId, String ownerfirstName, String ownerlastName, String street, String city, String state,
                 String zip, String propertyType) {
        this.houseId = houseId;
        this.ownerfirstName = ownerfirstName;
        this.ownerlastName = ownerlastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.propertyType = propertyType;
        this.location = String.format("http://localhost:8080/api/houses/%s", houseId);
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getOwnerfirstName() {
        return ownerfirstName;
    }

    public void setOwnerfirstName(String ownerfirstName) {
        this.ownerfirstName = ownerfirstName;
    }

    public String getOwnerlastName() {
        return ownerlastName;
    }

    public void setOwnerlastName(String ownerlastName) {
        this.ownerlastName = ownerlastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
