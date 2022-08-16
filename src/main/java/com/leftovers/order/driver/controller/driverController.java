package com.leftovers.order.driver.controller;


import com.leftovers.order.account.model.account;
import com.leftovers.order.account.service.accountService;
import com.leftovers.order.driver.model.driver;
import com.leftovers.order.restaurant.model.restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/driver")
public class driverController {

    @Autowired
    com.leftovers.order.driver.service.driverService driverService;
    @Autowired
    com.leftovers.order.account.service.accountService accountService;


    @RequestMapping
    public String IsItWorking() {
        return "Driver is working!";
    }

    @GetMapping(path = "/AddPredefined")
    public String AddPredefined()
    {
        //Optional<account> newOpt = accountService.getAccountById(3);
        //if(newOpt.isEmpty())
        //{
        //    return "Failed to obtain account";
        //}
/*
        driver newDriver = new driver();
        newDriver.setId(3);
        newDriver.setLicensePlate("SRT 345");
        newDriver.setRating(3.3);
        driverService.addDriver(newDriver);
  */      return "Complete?";
    }


    /*
    @GetMapping(path = "")
    public List<driver> getAll() {
        return driverService.getAll();
    }
    */

    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addDriver(@RequestParam driver newDriver) {
        System.out.println(newDriver.getName());
        if (driverService.addDriver(newDriver))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }*/

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<driver> getDriverById(@PathVariable Integer id) {
        return driverService.getDriverById(id);
    }
/*
    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<driver> getFoodByName(@PathVariable String name) {
        return driverService.getDriverByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateDriverName(@PathVariable Integer id , @RequestParam String name ) {
        if (driverService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    end minimalizing commenting/*


    /*      drivers don't have prices
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (driverService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  drivers don't have descriptions
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

    /*  drivers don't have restaurants
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
