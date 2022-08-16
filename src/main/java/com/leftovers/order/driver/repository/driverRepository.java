package com.leftovers.order.driver.repository;

import com.leftovers.order.driver.model.driver;
import org.springframework.data.repository.CrudRepository;


public interface driverRepository extends CrudRepository<driver, Integer> {
        driver findById(long id);
}