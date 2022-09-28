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
public class CreateOrderDto {

    @NotNull
    //@NotBlank(message = "Customer is required")
    //public Customer customer;
    public Integer customerId;

    @NotNull
    //@NotBlank(message = "Restaurant is required")
    //public Restaurant restaurant;
    public Integer restaurantId;

    //@NotNull
    //@NotBlank(message = "Driver is required")
    //public Driver driver;
    public Integer driverId;

    //public Discount discount;
    public Integer discountId;

    public Time time;
    //public EnumOrderStatus status;
    public String status;

    public BigDecimal price;

}