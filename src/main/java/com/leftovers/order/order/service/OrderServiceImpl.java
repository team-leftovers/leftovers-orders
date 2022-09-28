package com.leftovers.order.order.service;



import com.leftovers.order.order.dto.CreateOrderDto;
import com.leftovers.order.order.dto.TransmitOrderDto;
import com.leftovers.order.order.dto.UpdateOrderDto;
import com.leftovers.order.order.model.*;

import com.leftovers.order.order.repository.*;

import com.leftovers.order.order.exception.NoSuchOrderException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
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

        //get dto data
//        Integer orderId         = dto.getId();
        Integer driverId        = dto.getDriverId();
        Integer customerId      = dto.getCustomerId();
        Integer restaurantId    = dto.getRestaurantId();
        Integer discountId      = dto.getDiscountId();
        String status           = dto.getOrderStatus();
        Time deliveryTime       = dto.getDeliveryTime();
        BigDecimal totalPrice   = dto.getTotalPrice();
        Integer driverRating    = dto.getDriverRating();

        //cannot assign an ID even if I try
//        //if a value for orderId was included
//        if (orderId != null) {
//            //if the id is NOT available
//            if (!(verifyIdAvailability(orderId))) {
//                //find a way to throw errors, find a way to indicate invalid id
//                return Optional.empty();
//            }
//        }
//        //if any of the foreign keys are invalid, return an empty Optional
//        if(!(validateAllFKeys(customerId, restaurantId, driverId, discountId))) {
//            //throw new BadDtoException("Bad Dto");
//            return Optional.empty();
//        }

        //if a value for driverId was included
        if(driverId != null) {
            if (!(validateDriver(driverId))) {
                //if id is invalid
                //figure out how to throw exceptions properly
                return Optional.empty();
            }
        }

        //customerId is required
        if (!(validateCustomer(customerId))) {
            //if id is invalid
            //figure out how to throw exceptions properly
            return Optional.empty();
        }

        //restaurantId is required
        if (!(validateRestaurant(restaurantId))) {
            //if id is invalid
            //figure out how to throw exceptions properly
            return Optional.empty();
        }

        //if a value for discountId was included
        if(discountId != null) {
            if (!(validateDiscount(discountId))) {
                //if id is invalid
                //figure out how to throw exceptions properly
                return Optional.empty();
            }
        }

        //These items don't require validation? Maybe?
        //validate status
        //validate time
        //validate totalPrice
        //validate driverRating


        Order order = Order.builder()
//                .id(orderId)  // attempting to assign an ID doesnt work. Order keeps its auto-generated value
                .driverId(driverId)
                .customerId(customerId)
                .restaurantId(restaurantId)
                .discountId(discountId)
//                .discount(discountRepo.findById(dto.discountId).get())    //this sets the actual discount object, not just the id
                .status(status)
                .deliveryTime(deliveryTime)
                .totalPrice(totalPrice)
                .driverRating(driverRating)
                .build();



        return Optional.of(orderRepo.save(order));
    }



    @Transactional
    @Override
    public Optional<Order> updateOrder(Integer id, UpdateOrderDto dto)
    {
        notNull(id, dto);
        Optional<Order> result = orderRepo.findOrderById(id);
        if(result.isEmpty()) {
            throw new NoSuchOrderException(id);
        }

        //get dto data
        Integer driverId        = dto.getDriverId();
        Integer discountId      = dto.getDiscountId();
        String status           = dto.getOrderStatus();
        Time deliveryTime       = dto.getDeliveryTime();
        BigDecimal totalPrice   = dto.getTotalPrice();
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

        if(deliveryTime != null) {
            order.setDeliveryTime(deliveryTime);
        }

//        price should not be set-able, only calculated?
        if(totalPrice.doubleValue() != 0)
        {
            order.setTotalPrice(totalPrice);
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
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        for (Order i : orders)
        {
            i.updatePrice();
        }
        return orders;
    }

    @Override
    public Order getOrder(Integer id) {
        notNull(id);
        Order order = orderRepo.findOrderById(id)
                .orElseThrow(() -> new NoSuchOrderException(id));
        order.updatePrice();
        return order;
    }

    @Override
    public OrderItem getOrderItemFromOrder(Integer id, Integer index) {
        //check for negative index
        if (index < 0) {
            throw new IllegalArgumentException("List index cannot be negative");
        }

        //get order
        Order order = orderRepo.findOrderById(id)
                .orElseThrow(() -> new NoSuchOrderException(id));

        //found order, get item
        //check if index is within list bounds
        if (index >= order.getItems().size())  //if index exceeds bounds of the OrderItems List
        {
            throw new IllegalArgumentException("Index exceeds list bounds");
        }

        OrderItem item = order.getItems().get(index);

        return item;
    }

    @Override
    public Food getFoodFromOrder(Integer id, Integer index)
    {
        return getOrderItemFromOrder(id, index).getFood();
    }

    @Override
    public Boolean verifyIdAvailability(Integer orderId)
    {
        return !(orderRepo.verifyIdAvailability(orderId).isEmpty());
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

    public Integer getNewestId() {
        List<Order> orders = orderRepo.findAll();
        int testValue = 0;
        for (Order i : orders) {
            if (i.getId() > testValue) {
                testValue = i.getId();
            }
        }
        return testValue;
    }


    public HashMap<String, Object> getAllOrders(int pageNo, int pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        Pageable pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Order> pagedOrders = orderRepo.findAll(pageRequest);
        map.put("orders", pagedOrders.toList());
        map.put("totalItems", pagedOrders.getTotalElements());
        map.put("currentPage", pagedOrders.getNumber());
        map.put("totalPages", pagedOrders.getTotalPages());
        return map;
    }

    public Optional<TransmitOrderDto> createTransmitOrderDto(Order order)
    {
        TransmitOrderDto dto = TransmitOrderDto.builder()
                .id(order.getId())
                .driverId(order.getDriverId())
                .customerId(order.getCustomerId())
                .restaurantId(order.getRestaurantId())
                .status(order.getStatus())
                .deliveryTime(order.getDeliveryTime())
                .driverRating(order.getDriverRating())
                .totalPrice(order.updatePrice())
                .orderItemIds(new ArrayList<>())
                .build();

        for(OrderItem i : order.getItems())
        {
            dto.orderItemIds.add(i.getId());
        }

        return Optional.of(dto);
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