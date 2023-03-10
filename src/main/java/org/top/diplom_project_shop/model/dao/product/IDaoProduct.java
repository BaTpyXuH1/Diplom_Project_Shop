package org.top.diplom_project_shop.model.dao.product;


import org.top.diplom_project_shop.model.entity.Product;

import java.util.List;

public interface IDaoProduct {
    List<Product> listAll();

    Product getById(Integer id);


    Product add(Product product);


    Product update(Product product);


    Product delete(Integer id);
}
