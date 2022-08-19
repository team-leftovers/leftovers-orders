package com.leftovers.order.order.repository;

import com.leftovers.order.order.model.Address;
import com.leftovers.order.order.model.Account;
import com.leftovers.order.order.model.Customer;
import com.leftovers.order.order.model.Discount;
import com.leftovers.order.order.model.Driver;
import com.leftovers.order.order.model.EnumAccountType;
import com.leftovers.order.order.model.EnumOrderStatus;
import com.leftovers.order.order.model.Food;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.Restaurant;

import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<Discount, Integer> {
    Discount findDiscountById(Integer id);
}