package com.leftovers.order.product.model;


import com.leftovers.order.restaurant.model.restaurant;

import javax.persistence.*;

@Entity
@Table(name="product")
public class product {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name = "name")
    public String name;
/*
    @Column(name = "restaurant_id")
    public int restaurantId;
*/
    @Column(name = "price")
    public double price;

    @Column(name = "description")
    public String description;


    //@OneToOne(mappedBy = "productId")
    //public restaurant Restaurant;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    public restaurant restaurantId;



    public String displayId()
    {
        //return "\"id\":" + Integer.toString(id);
        return "\"id\":" + id;
    }


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

    /*
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
*/
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    //public restaurant getRestaurant() {return Restaurant;}

    //public void setRestaurant(restaurant restaurant) {Restaurant = restaurant;}


    @Override
    public String toString()
    {
        return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"restaurant_id\":" + restaurantId.getId() + ",\"price\":" + price + ",\"description\":\"" + description + "\"}";
    }


   /* // Overriding toString() method of String class
    @Override
    public String toString() {
        return this.id + " + " + this.latitude + "i";
    }*/
}
