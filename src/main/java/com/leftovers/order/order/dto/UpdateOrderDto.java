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

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class UpdateOrderDto {

    public Integer driverId = 0;

    public Integer discountId = 0;

    //public EnumOrderStatus status;
    public String status = "";

    //should probably not be able to set price, only calculate it
    //public BigDecimal price = new BigDecimal(0);

}