package com.leftovers.order.order.dao;


import com.leftovers.order.order.model.order;
import com.leftovers.order.order.repository.orderRepository;
import com.leftovers.order.order.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class orderDAO {

    @Autowired
    orderRepository fdr;

    public boolean addOrder(order newOrder) {

        try {
            fdr.save(newOrder);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<order> getOrderById(Integer id) {
        var result = fdr.findById(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    //public List<order> getOrderByCustomerId(@PathVariable int id) {
    public List<order> getOrderByCustomerId(int id) {
        var result = fdr.findByCustomerId(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    public List<order> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<order>) result;
        }
        else {
            return null;
        }
    }

    public boolean uptest(int dId, int cId, int rId, int status, double price, int diId)
    {
        order newOrder = new order();
        //newOrder.setId(5);
        newOrder.setDriverId(dId);
        newOrder.setCustomerId(cId);
        newOrder.setRestaurantId(rId);
        newOrder.setDiscountId(diId);
        newOrder.setStatus(status);
        newOrder.setTotalPrice(price);

        try {
            fdr.save(newOrder);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public boolean UpdateOrder(order updateOrder) {
        try {
            fdr.save(updateOrder);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
    public void deleteById(int id)
    {
        fdr.deleteById(id);
    }
    public boolean addOrder(int dId,int  cId, int rId, int diId, int status, double price){
        return fdr.addOrder(dId, cId, rId, diId, status, price);
    }
    public order updateDriverId(int oId, int dId)
    {
       Optional<order> result = fdr.findById(oId);
        if (result.isEmpty()) {
            return null;
        }
        order newOrder = result.get();
        newOrder.setDriverId(dId);

        try {
            fdr.save(newOrder);
        }
        catch (Exception e) {
            throw e;
        }
        return newOrder;
    }

    public order updateCustomerId(int oId, int cId) {

        Optional<order> result = fdr.findById(oId);
        if (result.isEmpty()) {
            return null;
        }
        order newOrder = result.get();
        newOrder.setCustomerId(cId);

        try {
            fdr.save(newOrder);
        }
        catch (Exception e) {
            throw e;
        }
        return newOrder;
    }
    public order updateRestaurantId(int oId, int rId) {

        Optional<order> result = fdr.findById(oId);
        if (result.isEmpty()) {
            return null;
        }
        order newOrder = result.get();
        newOrder.setRestaurantId(rId);

        try {
            fdr.save(newOrder);
        }
        catch (Exception e) {
            throw e;
        }
        return newOrder;
    }
    public order updateDiscountId(int oId, int dId) {

        Optional<order> result = fdr.findById(oId);
        if (result.isEmpty()) {
            return null;
        }
        order newOrder = result.get();
        newOrder.setDiscountId(dId);

        try {
            fdr.save(newOrder);
        }
        catch (Exception e) {
            throw e;
        }
        return newOrder;
    }
    public order updateStatus(int oId, int status) {

        Optional<order> result = fdr.findById(oId);
        if (result.isEmpty()) {
            return null;
        }
        order newOrder = result.get();
        newOrder.setStatus(status);

        try {
            fdr.save(newOrder);
        }
        catch (Exception e) {
            throw e;
        }
        return newOrder;
    }
    public order updatePrice(int oId, int price) {

        Optional<order> result = fdr.findById(oId);
        if (result.isEmpty()) {
            return null;
        }
        order newOrder = result.get();
        newOrder.setTotalPrice(price);

        try {
            fdr.save(newOrder);
        }
        catch (Exception e) {
            throw e;
        }
        return newOrder;
    }


}