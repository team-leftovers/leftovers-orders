package com.leftovers.order.product.dao;


import com.leftovers.order.product.model.product;
import com.leftovers.order.product.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class productDAO {

    @Autowired
    productRepository fdr;

    public boolean addProduct(product newProduct) {

        try {
            fdr.save(newProduct);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<product> getProductById(Integer id) {
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
            Optional<product> result = fdr.findById(id);
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
    public List<product> getProductByName(String name) {
        var result = fdr.findByName(name);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }
*/
    public List<product> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<product>) result;
        }
        else {
            return null;
        }
    }

    public boolean UpdateProduct(product updateProduct) {
        try {
            fdr.save(updateProduct);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
}