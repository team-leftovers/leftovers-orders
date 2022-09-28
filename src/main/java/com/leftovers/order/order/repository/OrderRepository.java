package com.leftovers.order.order.repository;

import com.leftovers.order.order.model.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findOrderById(Integer id);

    Optional<Order> findTopByOrderByIdDesc();

    //List<Order> findRestaurantByNameContainingIgnoreCase(String str);

    List<Order> findAll();
    Page<Order> findAll(Pageable pageable);
    int deleteOrderById(Integer id);

    @Query("SELECT u FROM Driver u WHERE u.id = ?1")
    List<Driver> validateDriver(Integer driverId);
    @Query("SELECT u FROM Customer u WHERE u.id = ?1")
    List<Customer> validateCustomer(Integer customerId);
    @Query("SELECT u FROM Restaurant u WHERE u.id = ?1")
    List<Restaurant> validateRestaurant(Integer restaurantId);
    @Query("SELECT u FROM Discount u WHERE u.id = ?1")
    List<Discount> validateDiscount(Integer discountId);

    //SELECT 1 WHERE EXISTS(SELECT * FROM tbl_customer WHERE account_id = 1) AND EXISTS(SELECT * FROM tbl_driver WHERE account_id = 2) AND EXISTS(SELECT * FROM tbl_restaurant WHERE id = 2) AND EXISTS(SELECT * FROM tbl_discount WHERE id = 1) LIMIT 1;

    //@Query("SELECT u FROM Order u WHERE EXISTS(SELECT * FROM tbl_customer WHERE account_id = 1) AND EXISTS(SELECT * FROM tbl_driver WHERE account_id = 2) AND EXISTS(SELECT * FROM tbl_restaurant WHERE id = 2) AND EXISTS(SELECT * FROM tbl_discount WHERE id = 1) LIMIT 1")
    //@Query(value = "SELECT u FROM Order u WHERE EXISTS(SELECT * FROM tbl_customer WHERE account_id = 1) AND EXISTS(SELECT * FROM tbl_driver WHERE account_id = 2) AND EXISTS(SELECT * FROM tbl_restaurant WHERE id = 2) AND EXISTS(SELECT * FROM tbl_discount WHERE id = 1) LIMIT 1",
    //@Query(value = "SELECT * FROM tbl_order WHERE id=1",
    //@Query(value = "SELECT 1 WHERE EXISTS(SELECT * FROM tbl_customer WHERE account_id = 1) AND EXISTS(SELECT * FROM tbl_driver WHERE account_id = 2) AND EXISTS(SELECT * FROM tbl_restaurant WHERE id = 2) AND EXISTS(SELECT * FROM tbl_discount WHERE id = 1) LIMIT 1",
    //@Query(value = "SELECT * FROM tbl_order WHERE EXISTS(SELECT * FROM tbl_customer WHERE account_id = 1) LIMIT 1",
    //The below one worked
    //@Query(value = "SELECT * FROM tbl_order WHERE EXISTS(SELECT * FROM tbl_driver WHERE account_id = 2) AND EXISTS(SELECT * FROM tbl_customer WHERE account_id = 1) AND EXISTS(SELECT * FROM tbl_restaurant WHERE id = 1) AND EXISTS(SELECT * FROM tbl_discount WHERE id = 1) LIMIT 1",

    @Query(value = "SELECT * FROM tbl_order WHERE EXISTS(SELECT * FROM tbl_order WHERE id = ?1) LIMIT 1",
            nativeQuery = true)
    public Optional<Order> verifyIdAvailability(Integer orderId);

    @Query(value = "SELECT * FROM tbl_order WHERE EXISTS(SELECT * FROM tbl_customer WHERE account_id = ?1) AND EXISTS(SELECT * FROM tbl_restaurant WHERE id = ?2) AND EXISTS(SELECT * FROM tbl_driver WHERE account_id = ?3) AND EXISTS(SELECT * FROM tbl_discount WHERE id = ?4) LIMIT 1",
            nativeQuery = true)
    Optional<Order> validateAllFKeys(Integer customerId, Integer restaurantId, Integer driverId, Integer discountId);

    @Query(value = "SELECT * FROM tbl_order WHERE EXISTS(SELECT * FROM tbl_customer WHERE account_id = ?1) AND EXISTS(SELECT * FROM tbl_restaurant WHERE id = ?2) LIMIT 1",
            nativeQuery = true)
    Optional<Order> validateRequiredFKeys(Integer customerId, Integer restaurantId);
   @Query(value = "SELECT * FROM tbl_order WHERE EXISTS(SELECT * FROM tbl_driver WHERE account_id = ?1) AND EXISTS(SELECT * FROM tbl_discount WHERE id = ?2) LIMIT 1",
            nativeQuery = true)
    Optional<Order> validateOptionalFKeys(Integer driverId, Integer discountId);
}
/*
import com.leftovers.order.order.model.order;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface orderRepository extends CrudRepository<order, Integer> {
        //order findById(long id);

       default  public List<order> findByCustomerId(int id)
       {
               List<order> orderList = new ArrayList<>();
               Iterable<order> orderIterable = findAll();
               for(order i: orderIterable)
               {
                       if(i.customerId == id)
                       {
                               orderList.add(i);
                       }
               }
                return orderList;
       }
        default public boolean addOrder(int dId,int  cId, int rId, int diId, int status, double price)
        {
                order newOrder = new order();
                newOrder.setId(5);
                newOrder.setDriverId(dId);
                newOrder.setCustomerId(cId);
                newOrder.setRestaurantId(rId);
                newOrder.setDiscountId(diId);
                newOrder.setStatus(status);
                newOrder.setTotalPrice(price);


                try
                {
                        save(newOrder);
                }
                catch (Exception e)
                {
                        throw e;
                }
                return true;
        }
/*


        /*
        default public order updateDriverId(int oId, int dId)
        {       order newOrder= findById(oId);
                newOrder.setDriverId(dId);
                return newOrder;
        }
        default public order updateCustomerId(int oId, int cId)
        {       order newOrder= findById(oId);
                newOrder.setCustomerId(cId);
                return newOrder;
        }
        default public order updateRestaurantId(int oId, int rId)
        {       order newOrder= findById(oId);
                newOrder.setRestaurantId(rId);
                return newOrder;
        }
        default public order updateDiscountId(int oId, int dId)
        {       order newOrder= findById(oId);
                newOrder.setDiscountId(dId);
                return newOrder;
        }
        default public order updateStatus(int oId, int status)
        {       order newOrder= findById(oId);
                newOrder.setStatus(status);
                return newOrder;
        }
        default public order updatePrice(int oId, int price)
        {       order newOrder= findById(oId);
                newOrder.setTotalPrice(price);
                return newOrder;
        }*/
    /*
}

*/