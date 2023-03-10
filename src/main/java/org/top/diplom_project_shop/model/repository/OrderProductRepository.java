package org.top.diplom_project_shop.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.diplom_project_shop.model.entity.OrderProduct;

public interface OrderProductRepository extends CrudRepository<OrderProduct,Integer> {
}
