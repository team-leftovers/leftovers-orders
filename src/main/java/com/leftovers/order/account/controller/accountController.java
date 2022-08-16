package com.leftovers.order.account.controller;


import com.leftovers.order.account.model.account;
import com.leftovers.order.account.service.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.leftovers.order.account.service.accountService;
import java.util.Optional;


@RestController
@RequestMapping(path = "/account")
public class accountController {

    @Autowired
    accountService accountService;


    @RequestMapping
    public String IsItWorking() {
        return "Account is working!";
    }

    @GetMapping(path = "/AddPredefined")
    public String AddPredefined()
    {
       /* account newAccount = new account();
        newAccount.setId(6);
        newAccount.setLatitude(4523);
        newAccount.setLongitude(0.01);
        newAccount.setZipCode(01256);
        newAccount.setCountry("Narnia");
        newAccount.setStreetAccount("lamppost");
        newAccount.setHouseNumber("HOUS7");
        newAccount.setUnitNumber("UN077");
        accountService.addAccount(newAccount);*/
        return "Complete?";
    }


    /*
    @GetMapping(path = "")
    public List<account> getAll() {
        return accountService.getAll();
    }
    */

    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addAccount(@RequestParam account newAccount) {
        System.out.println(newAccount.getName());
        if (accountService.addAccount(newAccount))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }*/

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<account> getAccountById(@PathVariable Integer id) {
        return accountService.getAccountById(id);
    }
/*
    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<account> getFoodByName(@PathVariable String name) {
        return accountService.getAccountByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateAccountName(@PathVariable Integer id , @RequestParam String name ) {
        if (accountService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    end minimalizing commenting/*


    /*      accounts don't have prices
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (accountService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  accounts don't have descriptions
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

    /*  accounts don't have restaurants
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
