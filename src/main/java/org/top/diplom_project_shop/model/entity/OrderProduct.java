package org.top.diplom_project_shop.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_product_t")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer quantityProduct;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    public OrderProduct() {}

    public OrderProduct(Integer quantityProduct, Product product) {
        this.quantityProduct = quantityProduct;
        this.product = product;
    }

    public OrderProduct(Integer quantityProduct, Order order) {
        this.quantityProduct = quantityProduct;
        this.order = order;
    }

    public OrderProduct(Integer id,Integer quantityProduct, Product product, Order order) {
        this.id = id;
        this.quantityProduct = quantityProduct;
        this.product = product;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", quantityProduct=" + quantityProduct +
                ", product=" + product +
                ", order=" + order +
                '}';
    }
}
