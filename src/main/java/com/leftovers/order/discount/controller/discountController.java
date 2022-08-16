package com.leftovers.order.discount.controller;


import com.leftovers.order.discount.model.discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/discount")
public class discountController {

    @Autowired
    com.leftovers.order.discount.service.discountService discountService;


    @RequestMapping
    public String IsItWorking() {
        return "Discount is working!";
    }

    @GetMapping(path = "/AddPredefined")
    public String AddPredefined()
    {
       /* discount newDiscount = new discount();
        newDiscount.setId(6);
        newDiscount.setLatitude(4523);
        newDiscount.setLongitude(0.01);
        newDiscount.setZipCode(01256);
        newDiscount.setCountry("Narnia");
        newDiscount.setStreetDiscount("lamppost");
        newDiscount.setHouseNumber("HOUS7");
        newDiscount.setUnitNumber("UN077");
        discountService.addDiscount(newDiscount);*/
        return "Complete?";
    }


    /*
    @GetMapping(path = "")
    public List<discount> getAll() {
        return discountService.getAll();
    }
    */

    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addDiscount(@RequestParam discount newDiscount) {
        System.out.println(newDiscount.getName());
        if (discountService.addDiscount(newDiscount))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }*/

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<discount> getDiscountById(@PathVariable Integer id) {
        return discountService.getDiscountById(id);
    }
/*
    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<discount> getFoodByName(@PathVariable String name) {
        return discountService.getDiscountByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateDiscountName(@PathVariable Integer id , @RequestParam String name ) {
        if (discountService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    end minimalizing commenting/*


    /*      discounts don't have prices
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (discountService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  discounts don't have descriptions
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

    /*  discounts don't have restaurants
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
