package com.leftovers.order.tag.controller;


import com.leftovers.order.tag.model.tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/tag")
public class tagController {

    @Autowired
    com.leftovers.order.tag.service.tagService tagService;
/*
    @RequestMapping
    public String IsItWorking() {
        return "Yes, it is working!";
    }
*/
    /*
    @GetMapping(path = "")
    public List<tag> getAll() {
        return tagService.getAll();
    }
    */

    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addTag(@RequestParam tag newTag) {
        System.out.println(newTag.getName());
        if (tagService.addTag(newTag))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }*/

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<tag> getTagById(@PathVariable Integer id) {
        return tagService.getTagById(id);
    }
/*
    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<tag> getFoodByName(@PathVariable String name) {
        return tagService.getTagByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateTagName(@PathVariable Integer id , @RequestParam String name ) {
        if (tagService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    end minimalizing commenting/*


    /*      tags don't have prices
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (tagService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  tags don't have descriptions
    @PutMapping(path = "/update-description/{id}")
    public String updateFoodDescription(@PathVariable Integer id , @RequestParam String description ) {
        if (foodService.updateFoodDescription(id , description)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  tags don't have restaurants
    @PutMapping(path = "/update-restaurant/{id}")
    public String updateFoodRestaurant(@PathVariable Integer id , @RequestParam Integer restaurantId ) {
        if (foodService.updateFoodRestaurant(id , restaurantId)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

/*
    @DeleteMapping(path = "/delete")
    public String deleteByid(@RequestParam Integer id) {
        return "del success";
    }*/
}
