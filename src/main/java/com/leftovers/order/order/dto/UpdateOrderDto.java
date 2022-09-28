package com.leftovers.order.order.dto;

import com.leftovers.order.order.model.Address;
import com.leftovers.order.order.model.Account;
import com.leftovers.order.order.model.Customer;
import com.leftovers.order.order.model.Driver;
import com.leftovers.order.order.model.Discount;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.Restaurant;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Time;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class UpdateOrderDto {

    private Integer driverId = 0;

    private Integer discountId = 0;

    //public EnumOrderStatus status;
    private String orderStatus = "";

    private Time deliveryTime;

    private BigDecimal totalPrice = new BigDecimal(0);

    private Integer driverRating = 0;

}