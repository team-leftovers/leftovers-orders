package com.leftovers.order.order.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Order.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

    @Column(name = "food_id", nullable = false)
    private Integer foodId;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "price", scope = com.leftovers.order.order.model.Food.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "food_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Food price;

    @Column(name = "quantity")
    private Integer quantity;


//    @Column(name = "price")
//    private BigDecimal price;


    @Column(name = "additional_instructions")
    private String additionalInstructions;

    @Column(name = "food_rating")
    private Integer foodRating;


}


/*
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Food.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumns
            (
                    {
                            @JoinColumn(name = "food_id", referencedColumnName = "id", insertable = false, updatable = false),
                            @JoinColumn(name = "price", referencedColumnName = "price", insertable = false, updatable = false)
                    }
            )
    private Food food;

*/