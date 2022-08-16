package com.leftovers.order.restaurant.model;
import com.leftovers.order.address.model.address;
//import com.leftovers.order.order.model.order;

import javax.persistence.*;

@Entity
@Table(name="restaurant")
public class restaurant {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "address_id")
    public int addressId;

    /*
    @OneToOne(fetch = FetchType.LAZY)//cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")//, referencedColumnName = "id")
    public address addressId;
*/

    @Column(name = "phone_number")
    public String phoneNumber;

    @Column(name = "website")
    public String website;

    @Column(name = "open_time")
    public String openTime;

    @Column(name = "closing_time")
    public String closingTime;

    @Column(name = "rating")
    public double rating;

    //@OneToOne(mappedBy = "restaurantId")
    //public order Order;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddressId(){return addressId;}

    public void setAddressId(int addressId) {this.addressId = addressId;}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

/*
    public address getAddressId() {
        return addressId;
    }

    public void setAddressId(address addressId) {
        this.addressId = addressId;
    }
    */
}
