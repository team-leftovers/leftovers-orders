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

import javax.persistence.Query;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final CustomerRepository customerRepo;
//    private final DiscountRepository discountRepo;
//    private final DriverRepository driverRepo;
    private final OrderRepository orderRepo;
//    private final RestaurantRepository restaurantRepo;

    @Transactional
    @Override
    public Order createNewOrder(CreateOrderDto dto) {
        notNull(dto);

        Order order = Order.builder()
                .driverId(dto.driverId)
                .customerId(dto.customerId)
                .restaurantId(dto.restaurantId)
//                .discount(discountRepo.findById(dto.discountId).get())
                .status(dto.status)
                .totalPrice(dto.price)
                .build();

        return orderRepo.save(order);
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
    public Order updateOrder(Integer id, UpdateOrderDto dto) {
        notNull(id, dto);
        Optional<Order> result = orderRepo.findOrderById(id);
        if(result.isEmpty())
            throw new NoSuchOrderException(id);

//        public UserEntity getUserByIdWithTypedQuery(Long id) {
//            TypedQuery<UserEntity> typedQuery
//                    = getEntityManager().createQuery("SELECT u FROM UserEntity u WHERE u.id=:id", UserEntity.class);
//            typedQuery.setParameter("id", id);
//            return typedQuery.getSingleResult();
//        }


//        public UserEntity getUserByIdWithPlainQuery(Long id) {
//            Query jpqlQuery = getEntityManager().createQuery("SELECT u FROM UserEntity u WHERE u.id=:id");
//            jpqlQuery.setParameter("id", id);
//            return (UserEntity) jpqlQuery.getSingleResult();
//        }

        Order order = result.get();
//        order.setDiscount(discountRepo.findById(dto.discountId).get());
        order.setDriverId(dto.driverId);
        order.setCustomerId(dto.customerId);
        order.setRestaurantId(dto.restaurantId);
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

    public Order test()
    {
        return orderRepo.test();
    }
}