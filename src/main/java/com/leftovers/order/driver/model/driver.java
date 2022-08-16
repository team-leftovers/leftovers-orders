package com.leftovers.order.driver.model;


import com.leftovers.order.account.model.account;
import com.leftovers.order.address.model.address;
//import com.leftovers.order.order.model.order;
import com.leftovers.order.restaurant.model.restaurant;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="driver")
public class driver {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    /*@Id
    @OneToOne(fetch = FetchType.LAZY)//cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")//, referencedColumnName = "id")
    public account accountId;
*/

    @Column(name = "license_plate", nullable = false)
    public String licensePlate;

    @Column(name = "rating", nullable = false)
    public double rating;

    //@OneToMany(mappedBy = "driverId")
    //public Set<order> Order;
    //@OneToOne(mappedBy = "driverId")
    //public order Order;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
