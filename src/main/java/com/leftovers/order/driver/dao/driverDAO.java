package com.leftovers.order.driver.dao;


import com.leftovers.order.driver.model.driver;
import com.leftovers.order.driver.repository.driverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class driverDAO {

    @Autowired
    driverRepository fdr;

    public boolean addDriver(driver newDriver) {

        try {
            fdr.save(newDriver);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<driver> getDriverById(Integer id) {
        var result = fdr.findById(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    /*
    public List<driver> getDriverByName(String name) {
        var result = fdr.findByName(name);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }
*/
    public List<driver> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<driver>) result;
        }
        else {
            return null;
        }
    }

    public boolean UpdateDriver(driver updateDriver) {
        try {
            fdr.save(updateDriver);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
}