package com.leftovers.order.order.dto;

import com.leftovers.order.order.model.Address;
import com.leftovers.order.order.model.Account;
import com.leftovers.order.order.model.Customer;
import com.leftovers.order.order.model.Driver;
import com.leftovers.order.order.model.Discount;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.Restaurant;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Time;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class UpdateOrderItemDto {

    public Integer foodId = 0;

    public Integer quantity = -1;

   // public BigDecimal price = new BigDecimal(-1);

    public String additionalInstructions = "";

    public Integer foodRating = -1;

}