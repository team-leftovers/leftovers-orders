package com.leftovers.order.address.model;


import com.leftovers.order.restaurant.model.restaurant;

import javax.persistence.*;

@Entity
@Table(name="address")
public class address {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name = "latitude")
    public double latitude;

    @Column(name = "longitude")
    public double longitude;

    @Column(name = "zip_code")
    public int zipCode;

    @Column(name = "country")
    public String country;

    @Column(name = "street_address")
    public String streetAddress;

    @Column(name = "house_number")
    public String houseNumber;
    @Column(name = "unit_number")
    public String unitNumber;


    //@OneToOne(mappedBy = "addressId")
    //public restaurant Restaurant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }




    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
/*
    public restaurant getRestaurant() {
        return Restaurant;
    }

    public void setRestaurant(restaurant restaurant) {
        Restaurant = restaurant;
    }
*/
   /* // Overriding toString() method of String class
    @Override
    public String toString() {
        return this.id + " + " + this.latitude + "i";
    }*/
}
