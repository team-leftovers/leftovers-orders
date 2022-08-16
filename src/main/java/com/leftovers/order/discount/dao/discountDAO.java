package com.leftovers.order.discount.dao;


import com.leftovers.order.discount.model.discount;
import com.leftovers.order.discount.repository.discountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class discountDAO {

    @Autowired
    discountRepository fdr;

    public boolean addDiscount(discount newDiscount) {

        try {
            fdr.save(newDiscount);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<discount> getDiscountById(Integer id) {
        var result = fdr.findById(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    /*
    public List<discount> getDiscountByName(String name) {
        var result = fdr.findByName(name);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }
*/
    public List<discount> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<discount>) result;
        }
        else {
            return null;
        }
    }

    public boolean UpdateDiscount(discount updateDiscount) {
        try {
            fdr.save(updateDiscount);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
}