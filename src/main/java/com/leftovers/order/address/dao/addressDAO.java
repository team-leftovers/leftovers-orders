package com.leftovers.order.address.dao;


import com.leftovers.order.address.repository.addressRepository;
import com.leftovers.order.address.model.address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class addressDAO {

    @Autowired
    addressRepository fdr;

    public boolean addAddress(address newAddress) {

        try {
            fdr.save(newAddress);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<address> getAddressById(Integer id) {
        var result = fdr.findById(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    public String readFromId(@PathVariable Integer id)
    {
        try {
            Optional<address> result = fdr.findById(id);
            if (result.isEmpty()) {
                return "empty";
            }
            return "full";
        }
        catch(Exception e)
        {
            //return "AtDAO";
            return e.getMessage();
        }
    }

    /*
    public List<address> getAddressByName(String name) {
        var result = fdr.findByName(name);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }
*/
    public List<address> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<address>) result;
        }
        else {
            return null;
        }
    }

    public boolean UpdateAddress(address updateAddress) {
        try {
            fdr.save(updateAddress);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
}