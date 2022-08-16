package com.leftovers.order.account.service;



import com.leftovers.order.account.dao.accountDAO;
import com.leftovers.order.account.model.account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class accountService {

    @Autowired
    accountDAO servDAO;
    public boolean addAccount(account newAccount) {
        try {
            servDAO.addAccount(newAccount);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }

    public Optional<account> getAccountById(Integer id) {
        try {
            return servDAO.getAccountById(id);
        }
        catch (Exception e) {
            throw e;
        }
    }
/*
    public List<account> getAccountByName(String name) {
        try {
            return servDAO.getAccountByName(name);
        }
        catch (Exception e) {
            throw e;
            throw e;
        }
    }
*/
    public List<account> getAll() {
        return servDAO.getAll();
    }

    public boolean updateFoodName(Integer id, String name) {
        Optional<account> accountFromDb = servDAO.getAccountById(id);
        account accountObject = accountFromDb.get();
        //accountObject.setName(name);
        if (servDAO.UpdateAccount(accountObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*      accounts don't have prices
    public boolean updateAccountPrice(Integer id, float price) {
        Optional<account> accountFromDb = foodDao.getFoodById(id);
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

    /*  accounts don't have descriptions
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

    /*  accounts don't have restaurants
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