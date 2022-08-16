package com.leftovers.order.tag.model;


import javax.persistence.*;

@Entity
@Table(name="tags")
public class tag {
    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name = "name")
    public String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
