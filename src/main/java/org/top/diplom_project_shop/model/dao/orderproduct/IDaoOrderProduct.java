package org.top.diplom_project_shop.model.dao.orderproduct;


import org.top.diplom_project_shop.model.entity.OrderProduct;

import java.util.List;

public interface IDaoOrderProduct {
    List<OrderProduct> listAll();

    OrderProduct getById(Integer id);


    OrderProduct add(OrderProduct orderProduct);


    OrderProduct update(OrderProduct orderProduct);


    OrderProduct delete(Integer id);
}
