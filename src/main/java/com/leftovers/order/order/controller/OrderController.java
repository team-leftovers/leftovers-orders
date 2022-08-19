package com.leftovers.order.order.controller;


import com.leftovers.order.order.dto.CreateOrderDto;
import com.leftovers.order.order.dto.UpdateOrderDto;
import com.leftovers.order.order.model.Address;
import com.leftovers.order.order.model.Account;
import com.leftovers.order.order.model.Customer;
import com.leftovers.order.order.model.Driver;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.Restaurant;

import com.leftovers.order.order.service.OrderService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/orders")
@RequiredArgsConstructor
public class OrderController {
    private static final String MAPPING = "/orders";
    private final OrderService service;


    @RequestMapping(path = "", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Order> postRestaurant(@Valid @RequestBody CreateOrderDto dto) {
        log.info("POST Order");
        var order = service.createNewOrder(dto);
        var uri = URI.create(MAPPING + "/" + order.getId());
        return ResponseEntity.created(uri).body(order);
    }



        @RequestMapping(path = "", method = RequestMethod.GET,
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
        public ResponseEntity<List<Order>> getAllOrders () {
            log.info("GET Order");
            var orders = service.getAllOrders();
            if (orders.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(orders);
        }

        @RequestMapping(path = "/{id}", method = RequestMethod.GET,
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
        public ResponseEntity<Order> getOrderById (@PathVariable Integer id){
        log.info("GET Order " + id);
        return ResponseEntity.of(Optional.ofNullable(service.getOrder(id)));
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String simplest_test()
    {
        return "Orders exists";
    }
    @RequestMapping(path = "/{id}/Driver", method = RequestMethod.GET)
    public Driver getDriverByOrderId (@PathVariable Integer id){
        log.info("GET Order " + id);
        Driver fetchedThing = service.getDriver(id);
        return fetchedThing;
    }
    @RequestMapping(path = "/{id}/Customer", method = RequestMethod.GET)
    public Customer getCustomerByOrderId (@PathVariable Integer id){
        log.info("GET Order " + id);
        Customer fetchedThing = service.getCustomer(id);
        return fetchedThing;
    }
    @RequestMapping(path = "/{id}/Restaurant", method = RequestMethod.GET)
    public Restaurant getRestaurantByOrderId (@PathVariable Integer id){
        log.info("GET Order " + id);
        Restaurant fetchedThing = service.getRestaurant(id);
        return fetchedThing;
    }
    @RequestMapping(path = "/{id}/Address", method = RequestMethod.GET)
    public Address getAddressByOrderId (@PathVariable Integer id){
        log.info("GET Order " + id);
        Address fetchedThing = service.getRestaurant(id).getAddress();
        return fetchedThing;
    }
    @RequestMapping(path = "/{id}/Account", method = RequestMethod.GET)
    public Account getAccountByOrderId (@PathVariable Integer id){
        log.info("GET Order " + id);
        Account fetchedThing = service.getDriver(id).getAccount();
        return fetchedThing;
    }
    @RequestMapping(path = "/{id}/Price", method = RequestMethod.GET)
    public String getPriceByOrderId (@PathVariable Integer id){
        log.info("GET Order " + id);
        String fetchedThing = service.getTotalPrice(id);
        return fetchedThing;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id,
                                                       @Valid @RequestBody UpdateOrderDto dto) {
        log.info("PUT Order " + id);
        return ResponseEntity.of(Optional.ofNullable(service.updateOrder(id, dto)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        log.info("DELETE Order " + id);
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
/*
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

    @RequestMapping(path = "/by-id/{id}" , method = RequestMethod.GET)
    public Optional<order> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }
   */





    /*
    @RequestMapping(path = "/add/{name}" , method = RequestMethod.POST)
    public String addOrder(@RequestParam order newOrder) {
        System.out.println(newOrder.getName());
        if (orderService.addOrder(newOrder))
        {
            return "Item Added Succesfully";
        }
        return "An Error Occured";
    }



    public String getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id).get().toString();
    }
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

    end minimalizing commenting





    orders don't have prices

      @PutMapping(path = "/update-price/{id}")
    public String updateFoodPricce(@PathVariable Integer id , @RequestParam float price ) {
        if (orderService.updateFoodPrice(id , price)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }




  orders don't have descriptions
    @PutMapping(path = "/update-description/{id}")
    public String updateFoodDescription(@PathVariable Integer id , @RequestParam String description ) {
        if (foodService.updateFoodDescription(id , description)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }



  orders don't have restaurants
    @PutMapping(path = "/update-restaurant/{id}")
    public String updateFoodRestaurant(@PathVariable Integer id , @RequestParam Integer restaurantId ) {
        if (foodService.updateFoodRestaurant(id , restaurantId)) {
            return "Updated Successfully";
        }
        else {
            return "and error occured";
        }
    }



    @DeleteMapping(path = "/delete")
    public String deleteByid(@RequestParam Integer id) {
        return "del success";
    }


    @RequestMapping
    public String IsItWorking() {
        return "Order is working!";
    }
*/

/*
}

 */
