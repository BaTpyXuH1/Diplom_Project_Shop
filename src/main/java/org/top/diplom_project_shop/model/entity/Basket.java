package org.top.diplom_project_shop.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "basket_t")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String description;
    @OneToOne(mappedBy = "basket")
    private Client client;
    @OneToMany
    @JoinColumn(name = "basket_id", nullable = false)
    private Set<OrderProduct> orderProductSet = new HashSet<>();


    public Basket() {
    }

    public Basket(Client client) {
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderProduct> getOrderProductSet() {
        return orderProductSet;
    }

    public void setOrderProductSet(Set<OrderProduct> orderProductSet) {
        this.orderProductSet = orderProductSet;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", client =  " + client.getId() +
                '}';
    }
}