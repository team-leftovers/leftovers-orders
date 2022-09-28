package com.leftovers.order.order.dto;

import com.leftovers.order.order.model.*;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransmitOrderDto {
    public Integer id;

    public Integer customerId;

    public Integer restaurantId;

    public Integer driverId;

    public Integer discountId;

    public String status;

    public Time deliveryTime;

    public BigDecimal totalPrice;

    public Integer driverRating;

    public List<Integer> orderItemIds;

}