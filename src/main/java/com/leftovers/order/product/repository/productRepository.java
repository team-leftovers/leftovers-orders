package com.leftovers.order.product.repository;

import com.leftovers.order.product.model.product;
import org.springframework.data.repository.CrudRepository;


public interface productRepository extends CrudRepository<product, Integer> {
        product findById(long id);

}