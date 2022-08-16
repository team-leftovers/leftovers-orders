package com.leftovers.order.product.service;



import com.leftovers.order.product.dao.productDAO;
import com.leftovers.order.product.model.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class productService {

    @Autowired
    productDAO servDAO;
    public boolean addProduct(product newProduct) {
        try {
            servDAO.addProduct(newProduct);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }

    public Optional<product> getProductById(Integer id) {
        try {
            return servDAO.getProductById(id);
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
    public List<product> getProductByName(String name) {
        try {
            return servDAO.getProductByName(name);
        }
        catch (Exception e) {
            throw e;
            throw e;
        }
    }
*/
    public List<product> getAll() {
        return servDAO.getAll();
    }

    public boolean updateFoodName(Integer id, String name) {
        Optional<product> productFromDb = servDAO.getProductById(id);
        product productObject = productFromDb.get();
        //productObject.setName(name);
        if (servDAO.UpdateProduct(productObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*      products don't have prices
    public boolean updateProductPrice(Integer id, float price) {
        Optional<product> productFromDb = foodDao.getFoodById(id);
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

    /*  products don't have descriptions
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

    /*  products don't have restaurants
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