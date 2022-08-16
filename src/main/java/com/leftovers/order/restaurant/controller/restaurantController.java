package com.leftovers.order.restaurant.controller;


import com.leftovers.order.address.model.address;
import com.leftovers.order.address.service.addressService;
import com.leftovers.order.restaurant.model.restaurant;
import com.leftovers.order.restaurant.service.restaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.Optional;


@RestController
@RequestMapping(path = "/restaurant")
public class restaurantController {

    @Autowired
    restaurantService restaurantService;

    @Autowired
    addressService addressService;


    @RequestMapping
    public String IsItWorking() {
        return "Restaurant is working!";
    }

    @GetMapping(path = "/AddPredefined")
    public String AddPredefined()
    {
       // public UserEntity getUserByIdWithPlainQuery(Long id) {
       // Query jpqlQuery = getEntityManager().createQuery("SELECT u FROM UserEntity u WHERE u.id=:id");
       //jpqlQuery.setParameter("id", id);
       // return (UserEntity) jpqlQuery.getSingleResult();

        Optional<address> addressOpt = addressService.getAddressById(6);
        if(addressOpt.isEmpty())
        {
            return "Failed to obtain address";
        }

        restaurant newRestaurant = new restaurant();
        newRestaurant.setName("Key Depot");
        newRestaurant.setAddressId(addressOpt.get());
        //newRestaurant.setAddressId(1);
        newRestaurant.setPhoneNumber("555-777-PLZ!");
        newRestaurant.setWebsite("key@foreign.com");
        newRestaurant.setOpenTime("12:00am");
        newRestaurant.setClosingTime("12:00pm");
        newRestaurant.setRating(11);
        restaurantService.addRestaurant(newRestaurant);


        return "Complete?";
    }


    /*
    @GetMapping(path = "")
    public List<restaurant> getAll() {
        return restaurantService.getAll();
    }
    */

    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addRestaurant(@RequestParam restaurant newRestaurant) {
        System.out.println(newRestaurant.getName());
        if (restaurantService.addRestaurant(newRestaurant))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }*/
/*
    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<restaurant> getRestaurantById(@PathVariable Integer id) {
        return restaurantService.getRestaurantById(id);
    }
    */

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public String getRestaurantById(@PathVariable Integer id) {
        Optional<restaurant> newRestaurant = restaurantService.getRestaurantById(id);
        if (newRestaurant.isEmpty())
        {
            return "null";
        }
        return newRestaurant.get().toString();
    }
/*
    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<restaurant> getFoodByName(@PathVariable String name) {
        return restaurantService.getRestaurantByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateRestaurantName(@PathVariable Integer id , @RequestParam String name ) {
        if (restaurantService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    end minimalizing commenting/*


    /*      restaurants don't have prices
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (restaurantService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  restaurants don't have descriptions
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

    /*  restaurants don't have restaurants
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
