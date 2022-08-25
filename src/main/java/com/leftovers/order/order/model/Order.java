package com.leftovers.order.order.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Restaurant restaurant;

    @Column(name = "driver_id")
    private Integer driverId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "driver_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Driver driver;


    @Column(name = "discount_id")
    private Integer discountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "discount_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Discount discount;

    @Column(name = "status", nullable = false)
    //@Builder.Default
    //private EnumOrderStatus status = EnumOrderStatus.error;
    private String status;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

}
