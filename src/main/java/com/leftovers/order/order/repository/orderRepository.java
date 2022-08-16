package com.leftovers.order.order.repository;

import com.leftovers.order.order.model.order;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface orderRepository extends CrudRepository<order, Integer> {
        //order findById(long id);

       default  public List<order> findByCustomerId(int id)
       {
               List<order> orderList = new ArrayList<>();
               Iterable<order> orderIterable = findAll();
               for(order i: orderIterable)
               {
                       if(i.customerId == id)
                       {
                               orderList.add(i);
                       }
               }
                return orderList;
       }
        default public boolean addOrder(int dId,int  cId, int rId, int diId, int status, double price)
        {
                order newOrder = new order();
                newOrder.setId(5);
                newOrder.setDriverId(dId);
                newOrder.setCustomerId(cId);
                newOrder.setRestaurantId(rId);
                newOrder.setDiscountId(diId);
                newOrder.setStatus(status);
                newOrder.setTotalPrice(price);


                try
                {
                        save(newOrder);
                }
                catch (Exception e)
                {
                        throw e;
                }
                return true;
        }

        /*
        default public order updateDriverId(int oId, int dId)
        {       order newOrder= findById(oId);
                newOrder.setDriverId(dId);
                return newOrder;
        }
        default public order updateCustomerId(int oId, int cId)
        {       order newOrder= findById(oId);
                newOrder.setCustomerId(cId);
                return newOrder;
        }
        default public order updateRestaurantId(int oId, int rId)
        {       order newOrder= findById(oId);
                newOrder.setRestaurantId(rId);
                return newOrder;
        }
        default public order updateDiscountId(int oId, int dId)
        {       order newOrder= findById(oId);
                newOrder.setDiscountId(dId);
                return newOrder;
        }
        default public order updateStatus(int oId, int status)
        {       order newOrder= findById(oId);
                newOrder.setStatus(status);
                return newOrder;
        }
        default public order updatePrice(int oId, int price)
        {       order newOrder= findById(oId);
                newOrder.setTotalPrice(price);
                return newOrder;
        }*/
}