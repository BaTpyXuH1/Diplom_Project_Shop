package org.top.diplom_project_shop.model.dao.orderproduct;


import jakarta.transaction.Transactional;
import org.top.diplom_project_shop.model.entity.OrderProduct;

import java.util.List;

public interface IDaoOrderProduct {
    @Transactional
    List<OrderProduct> listAll();
    @Transactional
    OrderProduct getById(Integer id);

    @Transactional
    OrderProduct add(OrderProduct orderProduct);

    @Transactional
    OrderProduct update(OrderProduct orderProduct);

    @Transactional
    OrderProduct delete(Integer id);
}
