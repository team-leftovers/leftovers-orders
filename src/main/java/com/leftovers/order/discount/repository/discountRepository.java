package com.leftovers.order.discount.repository;

import com.leftovers.order.discount.model.discount;
import org.springframework.data.repository.CrudRepository;


public interface discountRepository extends CrudRepository<discount, Integer> {
        discount findById(long id);
}