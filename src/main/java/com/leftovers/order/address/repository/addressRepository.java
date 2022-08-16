package com.leftovers.order.address.repository;

import com.leftovers.order.address.model.address;
import org.springframework.data.repository.CrudRepository;


public interface addressRepository extends CrudRepository<address, Integer> {
        address findById(long id);

}