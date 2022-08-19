package com.leftovers.order.order.service;



import com.leftovers.order.order.model.Customer;
import com.leftovers.order.order.model.Driver;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.Restaurant;

import com.leftovers.order.order.repository.OrderRepository;

import com.leftovers.order.order.exception.NoSuchOrderException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
/*
    @Transactional
    @Override
    public Order createNewOrder(CreateOrderDto dto) {
        notNull(dto);

        Order order = Order.builder()
                .driver(dto.driver)
                .customer(dto.customer)
                .restaurant(dto.restaurant)
                .discount(dto.discount)
                .status(dto.status)
                .totalPrice(dto.price)
                .build();

        return orderRepo.save(order);
    }
*/
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


    public Driver getDriver(Integer id) {
        notNull(id);
        Optional<Order> fetchedOrder = orderRepo.findOrderById(id);
        if(fetchedOrder.isEmpty())
        {
            return null;
        }
        //String fetched = fetchedOrder.get().getDriver().getLicensePlate();
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
        //String fetched = fetchedOrder.get().getDriver().getLicensePlate();
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
        //String fetched = fetchedOrder.get().getDriver().getLicensePlate();
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
        //String fetched = fetchedOrder.get().getDriver().getLicensePlate();
        String fetched = fetchedOrder.get().getTotalPrice().toString();
        return fetched;
    }
/*
    @Transactional
    @Override
    public Order updateOrder(Integer id, UpdateOrderDto dto) {
        notNull(id, dto);
        Optional<Order> result = orderRepo.findOrderById(id);
        if(result.isEmpty())
            throw new NoSuchOrderException(id);

        Order order = result.get();

        order.setDriver(dto.driver);
        order.setCustomer(dto.customer);
        order.setRestaurant(dto.restaurant);
        order.setDiscount(dto.discount);
        order.setStatus(dto.status);
        order.setTotalPrice(dto.price);

        return orderRepo.save(order);
    }

    @Transactional
    @Override
    public void deleteOrder(Integer id) {
        notNull(id);
        if(orderRepo.deleteOrderById(id) == 0)
            throw new NoSuchOrderException(id);
    }

   */

    // Utility function to determine if input was incorrectly null
    private void notNull(Object... ids) {
        for(var id: ids) {
            if(id == null)
                throw new IllegalArgumentException("Expected value but received null.");
        }
    }
}