package org.top.diplom_project_shop.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "product_t")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String productName;
    @Column
    private Integer productArticle;
    @Column
    private Integer productPrice;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String previewImage;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProductSet = new HashSet<>();



    public Product() {
        id = -1;
        productArticle = (int)(Math.random()*100000);

    }

    public Product(String productName, String previewImage) {
        this.productName = productName;
        this.previewImage = previewImage;
    }

    public Product(String productName, Integer productArticle, Integer productPrice, String previewImage) {
        this.productName = productName;
        this.productArticle = productArticle;
        this.productPrice = productPrice;
        this.previewImage = previewImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Integer getProductArticle() {
        return productArticle;
    }

    public void setProductArticle(Integer productArticle) {
        this.productArticle = productArticle;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    public Set<OrderProduct> getOrderProductSet() {
        return orderProductSet;
    }

    public void setOrderProductSet(Set<OrderProduct> orderProductSet) {
        this.orderProductSet = orderProductSet;
    }

    @Override
    public String toString() {
        return "Название товара : " + productName + " , " + "Артикул : " + productArticle +
                " , " + "Цена : " + productPrice + "$";
    }
}
