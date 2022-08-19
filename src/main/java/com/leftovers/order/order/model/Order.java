package com.leftovers.order.order.model;

/*
import com.leftovers.order.order.dto.CreateFoodDto;
import com.leftovers.order.order.dto.CreateRestaurantDto;
import com.leftovers.order.order.dto.UpdateFoodDto;
import com.leftovers.order.order.dto.UpdateRestaurantDto;
import com.leftovers.order.order.repository.AddressRepository;
import com.leftovers.order.order.repository.FoodRepository;
import com.leftovers.order.order.repository.RestaurantRepository;

import com.leftovers.order.order.service.FoodService;
import com.leftovers.order.order.service.FoodServiceImpl;
import com.leftovers.order.order.service.RestaurantService;
import com.leftovers.order.order.service.RestaurantServiceImpl;

import com.leftovers.order.order.exception.NoSuchFoodException;
import com.leftovers.order.order.exception.NoSuchRestaurantException;
*/

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.leftovers.order.order.model.Address;
import com.leftovers.order.order.model.Account;
import com.leftovers.order.order.model.Customer;
import com.leftovers.order.order.model.Discount;
import com.leftovers.order.order.model.Driver;
import com.leftovers.order.order.model.EnumAccountType;
import com.leftovers.order.order.model.EnumOrderStatus;
import com.leftovers.order.order.model.Food;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.Restaurant;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    //@JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "driver_id", referencedColumnName = "account_id", nullable = false)
    private Driver driver;



    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    //@JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "account_id", nullable = false)
    private Customer customer;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    //@JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    //@JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "discount_id", referencedColumnName = "id", nullable = true)
    private Discount discount;

    @Column(name = "status", nullable = false)
    //@Builder.Default
    //private EnumOrderStatus status = EnumOrderStatus.error;
    private String status;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

}

/*
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class order {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name = "driver_id")
    public int driverId;

    @Column(name = "customer_id")
    public int customerId;

    @Column(name = "restaurant_id")
    public int restaurantId;

    @Column(name = "discount_id")
    public int discountId;

    @Column(name = "status")
    public int status;

    @Column(name = "total_price")
    public double totalPrice;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
*/