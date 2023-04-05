package org.top.diplom_project_shop.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_t")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "isPaid",nullable = false)
    private boolean isPaid = false;

    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProductSet;

    public void pay(){
        this.isPaid = true;
    }

    public Order() {
        this.description = "";
    }

    public Order(String description, Client client) {
        this.description = description;
        this.client = client;
        this.orderProductSet = new HashSet<>();
    }



    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
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
        return  "Покупатель : " + client ;
    }
}
