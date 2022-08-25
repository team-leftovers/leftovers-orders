package com.leftovers.order.order.service;

import com.leftovers.order.order.dto.CreateOrderItemDto;
import com.leftovers.order.order.dto.UpdateOrderItemDto;
import com.leftovers.order.order.exception.NoSuchOrderException;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.OrderItem;
import com.leftovers.order.order.repository.OrderItemRepository;
import com.leftovers.order.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepo;

    public Optional<OrderItem> createNewOrderItem(CreateOrderItemDto dto)
    {
//        notNull(dto);

        Integer orderId         = dto.getOrderId();
        Integer foodId          = dto.getFoodId();
        Integer quantity        = dto.getQuantity();
        //BigDecimal price      = dto.getPrice();
        String additionalInstructions = dto.getAdditionalInstructions();
        //shouldn't ever have rating on create

        //if any of the foreign keys are invalid, return an empty Optional

        if(!(validateAllFKeys(orderId, foodId))) {
            //throw new BadDtoException("Bad Dto");
            return Optional.empty();
        }

        OrderItem orderItem = OrderItem.builder()
                .orderId(orderId)
                .foodId(foodId)
                .quantity(quantity)
                .additionalInstructions(additionalInstructions)
                .build();

        return Optional.of(orderItemRepo.save(orderItem));

    }

    public List<OrderItem> getAllOrderItems()
    {
        return orderItemRepo.findAll();
    }

    public OrderItem getOrderItem(Integer id)
    {
        notNull(id);
        return orderItemRepo.findOrderItemById(id)
                .orElseThrow(() -> new NoSuchOrderException(id));

    }

    public Optional<OrderItem> updateOrderItem(Integer id, UpdateOrderItemDto dto)
    {
        notNull(id, dto);
        Optional<OrderItem> result = orderItemRepo.findOrderItemById(id);
        if(result.isEmpty()) {
            throw new NoSuchOrderException(id);
        }

        //get dto data
        Integer foodId                  = dto.getFoodId();
        Integer quantity                = dto.getQuantity();
        //BigDecimal price              = dto.getPrice();
        String additionalInstructions   = dto.getAdditionalInstructions();
        Integer foodRating              = dto.getFoodRating();


        OrderItem orderItem = result.get();

        //if any of the foreign keys are invalid, return an empty Optional


        //compare all elements to default dto values to determine if they have been set
        if(foodId != 0) {
            if (!(validateFood(foodId))) {
                //throw new BadDtoException("Bad Dto");
                return Optional.empty();
            }
            orderItem.setFoodId(foodId);
        }

        if(quantity != -1)
        {
            //consider adding a "delete line" for quantity == 0
            orderItem.setQuantity(quantity);
        }

        if(additionalInstructions != "") {
            orderItem.setAdditionalInstructions(additionalInstructions);
        }

        if(foodRating != 0) {
            if(foodRating <= 5) {
                //some sort of thing to notify too high a rating? maybe an exception?
                orderItem.setFoodRating(foodRating);
            }
        }

        return Optional.ofNullable(orderItemRepo.save(orderItem));
    }
    @Transactional
    public void deleteOrderItem(Integer id)
    {
        notNull(id);
        if(orderItemRepo.deleteOrderItemById(id) == 0)
            throw new NoSuchOrderException(id);
    }

    public Boolean validateOrder(Integer orderId)
    {
        return !(orderItemRepo.validateOrder(orderId).isEmpty());
    }

    public Boolean validateFood(Integer foodId)
    {
        return !(orderItemRepo.validateFood(foodId).isEmpty());
    }

    public Boolean validateAllFKeys(Integer orderId, Integer foodId)
    {
        return !(orderItemRepo.validateAllFKeys(orderId, foodId).isEmpty());
    }

    // Utility function to determine if input was incorrectly null
    private void notNull(Object... ids) {
        for(var id: ids) {
            if(id == null)
                throw new IllegalArgumentException("Expected value but received null.");
        }
    }
}