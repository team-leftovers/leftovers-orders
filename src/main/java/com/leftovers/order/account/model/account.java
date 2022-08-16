package com.leftovers.order.account.model;


import com.leftovers.order.driver.model.driver;
//import com.leftovers.order.order.model.order;
import com.leftovers.order.restaurant.model.restaurant;

import javax.persistence.*;

@Entity
@Table(name="account")
public class account {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "email")
    public int email;

    @Column(name = "phone_no")
    public String phoneNo;

    @Column(name = "hashed_password")
    public String hashedPassword;

    @Column(name = "type")
    public int type;

    /*
    @OneToOne(mappedBy = "accountId")
    public driver Driver;

    @OneToOne(mappedBy = "customerId")
    public order Order;
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
