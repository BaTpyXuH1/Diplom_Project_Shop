package org.top.diplom_project_shop.model.dao.order;


import org.top.diplom_project_shop.model.entity.Order;

import java.util.List;

public interface IDaoOrder {
    List<Order> listAll();

    Order getById(Integer id);


    Order add(Order order);


    Order update(Order order);


    Order delete(Integer id);
}
