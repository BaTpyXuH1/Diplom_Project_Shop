package org.top.diplom_project_shop.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "catalog_t")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @OneToMany(mappedBy = "catalog",cascade = CascadeType.ALL)
    private Set<Product> productSet;

    public Catalog() {}

    public Catalog(String title, Set<Product> productSet) {
        this.title = title;
        this.productSet  = new HashSet<>();
    }

    public Catalog(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", productSet=" + productSet +
                '}';
    }
}
