package com.leftovers.order.order.service;

import com.leftovers.order.order.dto.CreateOrderDto;
import com.leftovers.order.order.dto.CreateOrderItemDto;
import com.leftovers.order.order.dto.UpdateOrderItemDto;
import com.leftovers.order.order.model.*;


import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    Optional<OrderItem> createNewOrderItem(CreateOrderItemDto dto);

    List<OrderItem> getAllOrderItems();

    OrderItem getOrderItem(Integer id);

    Optional<OrderItem> updateOrderItem(Integer id, UpdateOrderItemDto dto);

    void deleteOrderItem(Integer id);

    Boolean validateOrder(Integer orderId);

    Boolean validateFood(Integer foodId);

    Boolean validateAllFKeys(Integer orderId, Integer foodId);

}

/*


import com.leftovers.order.order.dao.orderDAO;
import com.leftovers.order.order.model.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class orderService {

    @Autowired
    orderDAO servDAO;
    public boolean addOrder(order newOrder) {
        try {
            servDAO.addOrder(newOrder);
        }
        catch (Exception e)
        {
            throw e;
        }
        return true;
    }
    public boolean uptest(int dId, int cId, int rId, int status, double price, int diId)
    {
            return servDAO.uptest(dId, cId, rId, status, price, diId);
    }
    public Optional<order> getOrderById(Integer id) {
        try {
            return servDAO.getOrderById(id);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public List<order> getOrderByCustomerId(@PathVariable int id) {
        return servDAO.getOrderByCustomerId(id);
    }
    public void deleteById(int id)
    {
        servDAO.deleteById(id);
    }
    public boolean addOrder(int dId,int  cId, int rId, int diId, int status, double price){
       return servDAO.addOrder(dId, cId, rId, diId, status, price);
    }
    public order updateDriverId( int oId, int dId) {
        return servDAO.updateDriverId(oId, dId);
    }

    public order updateCustomerId( int oId, int cId) {
        return servDAO.updateCustomerId(oId, cId);
    }
    public order updateRestaurantId( int oId, int rId) {
        return servDAO.updateRestaurantId(oId, rId);
    }
    public order updateDiscountId( int oId, int dId) {
        return servDAO.updateDiscountId(oId, dId);
    }
    public order updateStatus( int oId, int status) {
        return servDAO.updateStatus(oId, status);
    }
    public order updatePrice( int oId, int price) {
        return servDAO.updatePrice(oId, price);
    }

    public List<order> getAll() {
        return servDAO.getAll();
    }

    */
/*
    public boolean updateFoodName(Integer id, String name) {
        Optional<order> orderFromDb = servDAO.getOrderById(id);
        order orderObject = orderFromDb.get();
        //orderObject.setName(name);
        if (servDAO.UpdateOrder(orderObject))
        {
            return true;
        }
        else {
            return false;
        }
    }




          orders don't have prices
    public boolean updateOrderPrice(Integer id, float price) {
        Optional<order> orderFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setPrice(price);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }




      orders don't have descriptions
    public boolean updateFoodDescription(Integer id, String description) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setDescription(description);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }




     orders don't have restaurants
    public boolean updateFoodRestaurant(Integer id, Integer restaurantId) {
        Optional<Food> foodFromDb = foodDao.getFoodById(id);
        Food foodObject = foodFromDb.get();
        foodObject.setRestaurantId(restaurantId);
        if (foodDao.UpdateFood(foodObject))
        {
            return true;
        }
        else {
            return false;
        }
    }



    public List<order> getOrderByName(String name) {
        try {
            return servDAO.getOrderByName(name);
        }
        catch (Exception e) {
            throw e;
            throw e;
        }
    }
*/
/*
}

 */
