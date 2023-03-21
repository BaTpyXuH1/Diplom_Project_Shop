package org.top.diplom_project_shop.model.dao.order;


import jakarta.transaction.Transactional;
import org.top.diplom_project_shop.model.entity.Order;

import java.util.List;

public interface IDaoOrder {
    @Transactional
    List<Order> listAll();
    @Transactional
    Order getById(Integer id);

    @Transactional
    Order add(Order order);

    @Transactional
    Order update(Order order);

    @Transactional
    Order delete(Integer id);
}
