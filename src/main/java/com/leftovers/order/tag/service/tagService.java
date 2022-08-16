package com.leftovers.order.tag.service;



import com.leftovers.order.tag.dao.tagDAO;
import com.leftovers.order.tag.model.tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class tagService {

    @Autowired
    tagDAO servDAO;
    public boolean addTag(tag newTag) {
        try {
            servDAO.addTag(newTag);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }

    public Optional<tag> getTagById(Integer id) {
        try {
            return servDAO.getTagById(id);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public List<tag> getTagByName(String name) {
        try {
            return servDAO.getTagByName(name);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public List<tag> getAll() {
        return servDAO.getAll();
    }

    public boolean updateFoodName(Integer id, String name) {
        Optional<tag> tagFromDb = servDAO.getTagById(id);
        tag tagObject = tagFromDb.get();
        tagObject.setName(name);
        if (servDAO.UpdateTag(tagObject))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*      tags don't have prices
    public boolean updateTagPrice(Integer id, float price) {
        Optional<tag> tagFromDb = foodDao.getFoodById(id);
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

    /*  tags don't have descriptions
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

    /*  tags don't have restaurants
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