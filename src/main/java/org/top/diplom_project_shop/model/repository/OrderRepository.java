package org.top.diplom_project_shop.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.diplom_project_shop.model.entity.Order;

public interface OrderRepository extends CrudRepository<Order,Integer> {
}
