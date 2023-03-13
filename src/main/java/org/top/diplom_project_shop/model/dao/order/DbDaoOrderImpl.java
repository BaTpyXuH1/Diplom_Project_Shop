package org.top.diplom_project_shop.model.dao.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.top.diplom_project_shop.model.entity.Order;
import org.top.diplom_project_shop.model.repository.OrderRepository;

import java.util.List;

public class DbDaoOrderImpl implements IDaoOrder{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> listAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return null;
    }

    @Override
    public Order add(Order order) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public Order delete(Integer id) {
        return null;
    }
}
