package com.leftovers.order.discount.model;


//import com.leftovers.order.order.model.order;
//import com.leftovers.order.restaurant.model.restaurant;

import javax.persistence.*;

@Entity
@Table(name="discount")
public class discount {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name = "code")
    public String code;

    @Column(name = "percent")
    public double percent;

    @Column(name = "value")
    public double value;

/*
    @OneToOne(mappedBy = "discountId")
    public order Order;
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    /*
    public order getOrder() {
        return Order;
    }

    public void setOrder(order order) {
        Order = order;
    }
    */

}
