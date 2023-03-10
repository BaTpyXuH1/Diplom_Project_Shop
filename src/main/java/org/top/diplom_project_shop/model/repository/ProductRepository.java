package org.top.diplom_project_shop.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.diplom_project_shop.model.entity.Product;

public interface ProductRepository extends CrudRepository<Product,Integer> {
}
