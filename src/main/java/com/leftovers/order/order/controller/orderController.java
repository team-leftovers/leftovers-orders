package com.leftovers.order.order.controller;


import com.leftovers.order.order.model.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/order")
public class orderController {

    @Autowired
    com.leftovers.order.order.service.orderService orderService;

/*
    @RequestMapping
    public String IsItWorking() {
        return "Order is working!";
    }
*/
    @GetMapping(path = "/multi/{str1}/{str2}")
    public String multitest(@PathVariable String str1, @PathVariable String str2)
    {
        String newstring = str1 + str2;
        return newstring;
    }
    @GetMapping(path = "/test/{dId}/{cId}/{rId}/{status}/{price}/{diId}")
    public boolean uptest(@PathVariable int dId, @PathVariable int cId, @PathVariable int rId, @PathVariable int status, @PathVariable double price, @PathVariable int diId)
    {
        return orderService.uptest(dId, cId, rId, status, price, diId);
    }
    @GetMapping(path = "/AddPredefined")
    public String AddPredefined()
    {
        order newOrder = new order();
        newOrder.setId(6);
        newOrder.setDriverId(1);
        newOrder.setCustomerId(4);
        newOrder.setRestaurantId(5);
        newOrder.setDiscountId(3);
        newOrder.setStatus(2);
        newOrder.setTotalPrice(149.99);
        orderService.addOrder(newOrder);
        return "Complete?";
    }



    @GetMapping(path = "")
    public List<order> getAll() {
        return orderService.getAll();
    }

    @RequestMapping(path = "/customer/{id}" , method = RequestMethod.GET)
    public List<order> getOrderByCustomerId(@PathVariable int id) {
        return orderService.getOrderByCustomerId(id);
    }

    @RequestMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable int id)
    {
        orderService.deleteById(id);
    }


    @GetMapping(path = "/updateid/{oId}/driverid/{dId}")
    public order updateDriverId(@PathVariable int oId, @PathVariable int dId)
    {
        return orderService.updateDriverId(oId, dId);
    }

    @GetMapping(path = "/updateid/{oId}/customerid/{cId}")
    public order updateCustomerId(@PathVariable int oId, @PathVariable int cId)
    {
        return orderService.updateCustomerId(oId, cId);
    }
    @GetMapping(path = "/updateid/{oId}/restaurant/{rId}")
    public order updateRestaurantId(@PathVariable int oId, @PathVariable int rId)
    {
        return orderService.updateRestaurantId(oId, rId);
    }
    @GetMapping(path = "/updateid/{oId}/discountid/{dId}")
    public order updateDiscountId(@PathVariable int oId, @PathVariable int dId)
    {
        return orderService.updateDiscountId(oId, dId);
    }
    @GetMapping(path = "/updateid/{oId}/status/{status}")
    public order updateStatus(@PathVariable int oId, @PathVariable int status)
    {
        return orderService.updateStatus(oId, status);
    }

    @GetMapping(path = "/updateid/{oId}/price/{price}")
    public order updatePrice(@PathVariable int oId, @PathVariable int price)
    {
        return orderService.updatePrice(oId, price);
    }




    @GetMapping(path = "/add/driverid/{dId}/customerid/{cId}/restaurantid/{rId}/discountid/{diId}/status/{status}/price/{price}")// , method = RequestMethod.POST)
    public String addOrder(@PathVariable int dId, @PathVariable int cId, @PathVariable int rId, @PathVariable int diId, @PathVariable int status, @PathVariable double price)
    {
        boolean test = orderService.addOrder(dId, cId, rId, diId, status, price);
        if (test)
        {
            return "Item Added Successfully";
        }
        return "An Error Occurred";
    }
    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addOrder(@RequestParam order newOrder) {
        System.out.println(newOrder.getName());
        if (orderService.addOrder(newOrder))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }*/

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<order> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }
    /*
    public String getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id).get().toString();
    }
/*
    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public List<order> getFoodByName(@PathVariable String name) {
        return orderService.getOrderByName(name);
    }

    @PutMapping(path = "/update-name/{id}")
    public String updateOrderName(@PathVariable Integer id , @RequestParam String name ) {
        if (orderService.updateFoodName(id , name)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }

    end minimalizing commenting/*


    /*      orders don't have prices
    @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (orderService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }
*/

    /*  orders don't have descriptions
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

    /*  orders don't have restaurants
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
