package com.leftovers.order.restaurant.dao;


import com.leftovers.order.restaurant.model.restaurant;
import com.leftovers.order.restaurant.repository.restaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class restaurantDAO {

    @Autowired
    restaurantRepository fdr;

    public boolean addRestaurant(restaurant newRestaurant) {

        try {
            fdr.save(newRestaurant);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<restaurant> getRestaurantById(Integer id) {
        var result = fdr.findById(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    /*
    public List<restaurant> getRestaurantByName(String name) {
        var result = fdr.findByName(name);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }
*/
    public List<restaurant> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<restaurant>) result;
        }
        else {
            return null;
        }
    }

    public boolean UpdateRestaurant(restaurant updateRestaurant) {
        try {
            fdr.save(updateRestaurant);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
}