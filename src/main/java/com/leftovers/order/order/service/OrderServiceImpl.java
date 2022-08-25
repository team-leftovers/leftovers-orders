package com.leftovers.order.order.service;



import com.leftovers.order.order.dto.CreateOrderDto;
import com.leftovers.order.order.dto.UpdateOrderDto;
import com.leftovers.order.order.model.Customer;
import com.leftovers.order.order.model.Driver;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.Restaurant;

import com.leftovers.order.order.repository.*;

import com.leftovers.order.order.exception.NoSuchOrderException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;

    @Transactional
    @Override
    public Optional<Order> createNewOrder(CreateOrderDto dto) {
        notNull(dto);

        Integer customerId      = dto.getCustomerId();
        Integer restaurantId    = dto.getRestaurantId();
        Integer driverId        = dto.getDriverId();
        Integer discountId      = dto.getDiscountId();

    //if any of the foreign keys are invalid, return an empty Optional

        if(!(validateAllFKeys(customerId, restaurantId, driverId, discountId))) {
            //throw new BadDtoException("Bad Dto");
            return Optional.empty();
        }

        Order order = Order.builder()
                .driverId(dto.driverId)
                .customerId(dto.customerId)
                .restaurantId(dto.restaurantId)
//                .discount(discountRepo.findById(dto.discountId).get())
                .status(dto.status)
                .totalPrice(dto.price)
                .build();

        return Optional.of(orderRepo.save(order));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrder(Integer id) {
        notNull(id);
        return orderRepo.findOrderById(id)
                .orElseThrow(() -> new NoSuchOrderException(id));
    }


    @Transactional
    @Override
    public Optional<Order> updateOrder(Integer id, UpdateOrderDto dto) {
        notNull(id, dto);
        Optional<Order> result = orderRepo.findOrderById(id);
        if(result.isEmpty()) {
            throw new NoSuchOrderException(id);
        }

        //get dto data
        Integer driverId        = dto.getDriverId();
        Integer discountId      = dto.getDiscountId();
        String status           = dto.getStatus();
        Time deliveryTime       = dto.getDeliveryTime();
        //BigDecimal price      = dto.getPrice();
        Integer driverRating    = dto.getDriverRating();


        Order order = result.get();

        //if any of the foreign keys are invalid, return an empty Optional


        //compare all elements to default dto values to determine if they have been set
        if(driverId != 0) {
            if (!(validateDriver(driverId))) {
                //throw new BadDtoException("Bad Dto");
                return Optional.empty();
            }
            order.setDriverId(driverId);
        }
        if(discountId != 0) {
            if (!(validateDiscount(discountId))) {
                return Optional.empty();
            }
            order.setDiscountId(discountId);
        }

        if(!(status.contentEquals (""))){
            order.setStatus(status);
        }

        //price should not be set-able, only calculated0
//        if(price.doubleValue() != 0)
//        {
//            order.setTotalPrice(price);
//        }


        if(deliveryTime != null) {
            order.setDeliveryTime(deliveryTime);
        }

        if(driverRating != 0) {
            if(driverRating <= 5) {
                //some sort of thing to notify too high a rating? maybe an exception?
                order.setDriverRating(driverRating);
            }
        }

        return Optional.ofNullable(orderRepo.save(order));
    }

    @Override
    public Boolean validateAllFKeys(Integer customerId, Integer restaurantId, Integer driverId, Integer discountId)
    {
        return !(orderRepo.validateAllFKeys(customerId, restaurantId, driverId, discountId).isEmpty());
    }
    @Override
    public Boolean validateRequiredFKeys(Integer customerId, Integer restaurantId)
    {
        return !(orderRepo.validateRequiredFKeys(customerId, restaurantId).isEmpty());
    }
    @Override
    public Boolean validateOptionalFKeys(Integer driverId, Integer discountId)
    {
        return !(orderRepo.validateOptionalFKeys(driverId, discountId).isEmpty());
    }
    @Transactional
    @Override
    public void deleteOrder(Integer id) {
        notNull(id);
        if(orderRepo.deleteOrderById(id) == 0)
            throw new NoSuchOrderException(id);
    }

    public Driver getDriver(Integer id) {
        notNull(id);
        Optional<Order> fetchedOrder = orderRepo.findOrderById(id);
        if(fetchedOrder.isEmpty())
        {
            return null;
        }
        Driver fetched = fetchedOrder.get().getDriver();
        return fetched;
    }

    public Customer getCustomer(Integer id){
        notNull(id);
        Optional<Order> fetchedOrder = orderRepo.findOrderById(id);
        if(fetchedOrder.isEmpty())
        {
            return null;
        }
        Customer fetched = fetchedOrder.get().getCustomer();
        return fetched;
    }
    public Restaurant getRestaurant(Integer id){
        notNull(id);
        Optional<Order> fetchedOrder = orderRepo.findOrderById(id);
        if(fetchedOrder.isEmpty())
        {
            return null;
        }
        Restaurant fetched = fetchedOrder.get().getRestaurant();
        return fetched;
    }

    public String getTotalPrice(Integer id){
        notNull(id);
        Optional<Order> fetchedOrder = orderRepo.findOrderById(id);
        if(fetchedOrder.isEmpty())
        {
            return null;
        }
        String fetched = fetchedOrder.get().getTotalPrice().toString();
        return fetched;
    }



    // Utility function to determine if input was incorrectly null
    private void notNull(Object... ids) {
        for(var id: ids) {
            if(id == null)
                throw new IllegalArgumentException("Expected value but received null.");
        }
    }

    public Boolean validateDriver(Integer driverId)
    {
        return !(orderRepo.validateDriver(driverId).isEmpty());
    }
    public Boolean validateCustomer(Integer customerId)
    {
        return !(orderRepo.validateCustomer(customerId).isEmpty());
    }
    public Boolean validateRestaurant(Integer restaurantId)
    {
        return !(orderRepo.validateRestaurant(restaurantId).isEmpty());
    }
    public Boolean validateDiscount(Integer discountId)
    {
        return !(orderRepo.validateDiscount(discountId).isEmpty());
    }

    /*public Boolean validateFKeys(Integer driverId, Integer customerId, Integer restaurantId, Integer discountId)
    {

        //this is a clearly non-optimal approach, but it will do for now

        if(!(validateDriver(driverId))) {
            return false;
        }

        if(!(validateCustomer(customerId))) {
            return false;
        }

        if(!(validateRestaurant(restaurantId))) {
            return false;
        }

        if(!(validateDiscount(discountId))) {
            return false;
        }

        return true;
    }*/
//    public Boolean validateFKeys(Integer driverId, Integer customerId, Integer restaurantId, Integer discountId)
//    {
//
//        //this is a clearly non-optimal approach, but it will do for now
//
//
//        //      return orderRepo.findOrderById(id)
//        //                .orElseThrow(() -> new NoSuchOrderException(id));
//
//        if(!(validateDriver(driverId)))
//        {
//            //throw new BadDtoException("Bad Driver ID");
//            return false;
//        }
//
//        if(!(validateCustomer(customerId))) {
//            return false;
//        }
//
//        if(!(validateRestaurant(restaurantId))) {
//            return false;
//        }
//
//        if(!(validateDiscount(discountId))) {
//            return false;
//        }
//
//        return true;
//    }
}