package com.leftovers.order.order.model;

import com.leftovers.order.order.model.Address;
import com.leftovers.order.order.model.Account;
import com.leftovers.order.order.model.Customer;
import com.leftovers.order.order.model.Discount;
import com.leftovers.order.order.model.Driver;
import com.leftovers.order.order.model.Food;
import com.leftovers.order.order.model.Order;
import com.leftovers.order.order.model.Restaurant;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;

@Entity
//@IdClass(Account.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_customer")
public class Customer //implements Serializable
{


/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "address_id", nullable = false)
        private Integer addressId;
     */
/*
    @Id
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Customer.class)
    @JsonIdentityReference(alwaysAsId = true)
    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;
*/

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Customer.class)
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address address;


    @Id
    @Column(name = "account_id", nullable = false)
    private Integer id;

    @OneToOne
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Customer.class)
    //@JsonIdentityReference(alwaysAsId = true)
    @PrimaryKeyJoinColumn(name = "account_id")//, referencedColumnName = "id")
    private Account account;

    /*
    @Id
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = com.leftovers.order.order.model.Customer.class)
    //@JsonIdentityReference(alwaysAsId = true)
    //@OneToOne(fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Account account;
*/

/*
    @OneToMany
    private List<Order> orders;
*/
}