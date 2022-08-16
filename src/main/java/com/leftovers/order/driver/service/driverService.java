package com.leftovers.order.driver.service;



import com.leftovers.order.driver.dao.driverDAO;
import com.leftovers.order.driver.model.driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class driverService {

    @Autowired
    driverDAO servDAO;
    public boolean addDriver(driver newDriver) {
        try {
            servDAO.addDriver(newDriver);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }

    public Optional<driver> getDriverById(Integer id) {
        try {
            return servDAO.getDriverById(id);
        }
        catch (Exception e) {
            throw e;
        }
    }
/*
    public List<driver> getDriverByName(String name) {
        try {
            return servDAO.getDriverByName(name);
        }
        catch (Exception e) {
            throw e;
            throw e;
        }
    }
*/
    public List<driver> getAll() {
        return servDAO.getAll();
    }

    public boolean updateFoodName(Integer id, String name) {
        Optional<driver> driverFromDb = servDAO.getDriverById(id);
        driver driverObject = driverFromDb.get();
        //driverObject.setName(name);
        if (servDAO.UpdateDriver(driverObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*      drivers don't have prices
    public boolean updateDriverPrice(Integer id, float price) {
        Optional<driver> driverFromDb = foodDao.getFoodById(id);
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

    /*  drivers don't have descriptions
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

    /*  drivers don't have restaurants
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