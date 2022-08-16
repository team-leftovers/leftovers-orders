package com.leftovers.order.discount.service;



import com.leftovers.order.discount.dao.discountDAO;
import com.leftovers.order.discount.model.discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class discountService {

    @Autowired
    discountDAO servDAO;
    public boolean addDiscount(discount newDiscount) {
        try {
            servDAO.addDiscount(newDiscount);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }

    public Optional<discount> getDiscountById(Integer id) {
        try {
            return servDAO.getDiscountById(id);
        }
        catch (Exception e) {
            throw e;
        }
    }
/*
    public List<discount> getDiscountByName(String name) {
        try {
            return servDAO.getDiscountByName(name);
        }
        catch (Exception e) {
            throw e;
            throw e;
        }
    }
*/
    public List<discount> getAll() {
        return servDAO.getAll();
    }

    public boolean updateFoodName(Integer id, String name) {
        Optional<discount> discountFromDb = servDAO.getDiscountById(id);
        discount discountObject = discountFromDb.get();
        //discountObject.setName(name);
        if (servDAO.UpdateDiscount(discountObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*      discounts don't have prices
    public boolean updateDiscountPrice(Integer id, float price) {
        Optional<discount> discountFromDb = foodDao.getFoodById(id);
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

    /*  discounts don't have descriptions
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

    /*  discounts don't have restaurants
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