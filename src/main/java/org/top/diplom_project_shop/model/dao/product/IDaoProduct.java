package org.top.diplom_project_shop.model.dao.product;


import jakarta.transaction.Transactional;
import org.top.diplom_project_shop.model.entity.Product;

import java.util.List;

public interface IDaoProduct {
    @Transactional
    List<Product> listAll();
    @Transactional
    Product getById(Integer id);

    @Transactional
    Product add(Product product);
    @Transactional
    Product upPrice(Product product);
    @Transactional
    Product upTitle(Product product);

    @Transactional
    Product update(Product product);

    @Transactional
    Product delete(Integer id);
}
