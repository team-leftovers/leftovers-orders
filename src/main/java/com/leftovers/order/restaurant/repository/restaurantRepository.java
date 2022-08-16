package com.leftovers.order.restaurant.repository;

import com.leftovers.order.restaurant.model.restaurant;
import org.springframework.data.repository.CrudRepository;


public interface restaurantRepository extends CrudRepository<restaurant, Integer> {
        restaurant findById(long id);
}