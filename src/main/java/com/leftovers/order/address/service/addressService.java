package com.leftovers.order.address.service;



import com.leftovers.order.address.dao.addressDAO;
import com.leftovers.order.address.model.address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class addressService {

    @Autowired
    addressDAO servDAO;
    public boolean addAddress(address newAddress) {
        try {
            servDAO.addAddress(newAddress);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }

    public Optional<address> getAddressById(Integer id) {
        try {
            return servDAO.getAddressById(id);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String readFromId(@PathVariable Integer id) {
        try {
            return servDAO.readFromId(id);
        }
        catch(Exception e)
        {
            return "At Service";

        }
    }
/*
    public List<address> getAddressByName(String name) {
        try {
            return servDAO.getAddressByName(name);
        }
        catch (Exception e) {
            throw e;
            throw e;
        }
    }
*/
    public List<address> getAll() {
        return servDAO.getAll();
    }

    public boolean updateFoodName(Integer id, String name) {
        Optional<address> addressFromDb = servDAO.getAddressById(id);
        address addressObject = addressFromDb.get();
        //addressObject.setName(name);
        if (servDAO.UpdateAddress(addressObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*      addresss don't have prices
    public boolean updateAddressPrice(Integer id, float price) {
        Optional<address> addressFromDb = foodDao.getFoodById(id);
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

    /*  addresss don't have descriptions
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

    /*  addresss don't have restaurants
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