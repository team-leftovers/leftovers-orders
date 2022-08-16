package com.leftovers.order.product.controller;


import com.leftovers.order.product.model.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/product")
public class productController {

    @Autowired
    com.leftovers.order.product.service.productService productService;


    @RequestMapping
    public String IsItWorking() {
        return "Product is working!";
    }

    @GetMapping(path = "/AddPredefined")
    public String AddPredefined()
    {
       /* product newProduct = new product();
        newProduct.setId(6);
        newProduct.setLatitude(4523);
        newProduct.setLongitude(0.01);
        newProduct.setZipCode(01256);
        newProduct.setCountry("Narnia");
        newProduct.setStreetProduct("lamppost");
        newProduct.setHouseNumber("HOUS7");
        newProduct.setUnitNumber("UN077");
        productService.addProduct(newProduct);*/
        return "Complete?";
    }



    @GetMapping(path = "/all")
    public List<product> getAll() {
        return productService.getAll();
    }


    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addProduct(@RequestParam product newProduct) {
        System.out.println(newProduct.getName());
        if (productService.addProduct(newProduct))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }*/

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<product> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }
    @RequestMapping(path = "/showid/{id}" , method = RequestMethod.GET)
    public String showId(@PathVariable Integer id) {
        Optional<product> newProduct = productService.getProductById(id);
        if(newProduct.isEmpty())
        {
            return "null";
        }
        return newProduct.get().toString();
    }
    @RequestMapping(path = "/read/{id}" , method = RequestMethod.GET)
    public String readFromId(@PathVariable Integer id)
    {
        try {
            return productService.readFromId(id);

        }
        catch(Exception e)
        {
            return "At controller";
        }
    }
    /*
    public String getProductById(@PathVariable Integer id) {
        return productService.getProductById(id).get().toString();
    }
/*
    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<product> getFoodByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateProductName(@PathVariable Integer id , @RequestParam String name ) {
        if (productService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    end minimalizing commenting/*


    /*      products don't have prices
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (productService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  products don't have descriptions
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

    /*  products don't have restaurants
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
