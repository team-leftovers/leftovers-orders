package com.leftovers.order.address.controller;


import com.leftovers.order.address.model.address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

//import com.leftovers.order.address.service.addressService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/address")
public class addressController {

    @Autowired
    com.leftovers.order.address.service.addressService addressService;


    @RequestMapping
    public String IsItWorking() {
        return "Address is working!";
    }

    @GetMapping(path = "/AddPredefined")
    public String AddPredefined()
    {
       /* address newAddress = new address();
        newAddress.setId(6);
        newAddress.setLatitude(4523);
        newAddress.setLongitude(0.01);
        newAddress.setZipCode(01256);
        newAddress.setCountry("Narnia");
        newAddress.setStreetAddress("lamppost");
        newAddress.setHouseNumber("HOUS7");
        newAddress.setUnitNumber("UN077");
        addressService.addAddress(newAddress);*/
        return "Complete?";
    }



    @GetMapping(path = "/all")
    public List<address> getAll() {
        return addressService.getAll();
    }


    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addAddress(@RequestParam address newAddress) {
        System.out.println(newAddress.getName());
        if (addressService.addAddress(newAddress))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }*/

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<address> getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }
    @RequestMapping(path = "/showid/{id}" , method = RequestMethod.GET)
    public String showId(@PathVariable Integer id) {
        Optional<address> newAddress = addressService.getAddressById(id);
        if(newAddress.isEmpty())
        {
            return "null";
        }
        return newAddress.get().toString();
    }
    @RequestMapping(path = "/read/{id}" , method = RequestMethod.GET)
    public String readFromId(@PathVariable Integer id)
    {
        try {
            return addressService.readFromId(id);

        }
        catch(Exception e)
        {
            return "At controller";
        }
    }
    /*
    public String getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id).get().toString();
    }
/*
    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<address> getFoodByName(@PathVariable String name) {
        return addressService.getAddressByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateAddressName(@PathVariable Integer id , @RequestParam String name ) {
        if (addressService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    end minimalizing commenting/*


    /*      addresss don't have prices
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (addressService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  addresss don't have descriptions
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

    /*  addresss don't have restaurants
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
