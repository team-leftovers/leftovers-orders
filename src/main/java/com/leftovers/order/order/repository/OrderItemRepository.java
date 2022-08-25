package com.leftovers.order.order.repository;

import com.leftovers.order.order.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    Optional<OrderItem> findOrderItemById(Integer id);

    List<OrderItem> findAll();

    int deleteOrderItemById(Integer id);

    @Query("SELECT u FROM Order u WHERE u.id = ?1")
    List<Order> validateOrder(Integer orderId);
    @Query("SELECT u FROM Food u WHERE u.id = ?1")
    List<Food> validateFood(Integer foodId);

    @Query(value = "SELECT * FROM tbl_order_item WHERE EXISTS(SELECT * FROM tbl_order WHERE id = ?1) AND EXISTS(SELECT * FROM tbl_food WHERE id = ?2) LIMIT 1",
            nativeQuery = true)
    Optional<OrderItem> validateAllFKeys(Integer orderId, Integer foodId);
}