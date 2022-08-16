package com.leftovers.order.restaurant.service;



import com.leftovers.order.restaurant.dao.restaurantDAO;
import com.leftovers.order.restaurant.model.restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class restaurantService {

    @Autowired
    restaurantDAO servDAO;
    public boolean addRestaurant(restaurant newRestaurant) {
        try {
            servDAO.addRestaurant(newRestaurant);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }

    public Optional<restaurant> getRestaurantById(Integer id) {
        try {
            return servDAO.getRestaurantById(id);
        }
        catch (Exception e) {
            throw e;
        }
    }
/*
    public List<restaurant> getRestaurantByName(String name) {
        try {
            return servDAO.getRestaurantByName(name);
        }
        catch (Exception e) {
            throw e;
            throw e;
        }
    }
*/
    public List<restaurant> getAll() {
        return servDAO.getAll();
    }

    public boolean updateFoodName(Integer id, String name) {
        Optional<restaurant> restaurantFromDb = servDAO.getRestaurantById(id);
        restaurant restaurantObject = restaurantFromDb.get();
        //restaurantObject.setName(name);
        if (servDAO.UpdateRestaurant(restaurantObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*      restaurants don't have prices
    public boolean updateRestaurantPrice(Integer id, float price) {
        Optional<restaurant> restaurantFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setPrice(price);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }
    */

    /*  restaurants don't have descriptions
    public boolean updateFoodDescription(Integer id, String description) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setDescription(description);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }
     */

    /*  restaurants don't have restaurants
    public boolean updateFoodRestaurant(Integer id, Integer restaurantId) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setRestaurantId(restaurantId);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }
    */

}