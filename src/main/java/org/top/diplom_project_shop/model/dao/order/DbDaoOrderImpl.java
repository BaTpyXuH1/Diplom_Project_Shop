package org.top.diplom_project_shop.model.dao.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.diplom_project_shop.model.entity.Client;
import org.top.diplom_project_shop.model.entity.Order;
import org.top.diplom_project_shop.model.repository.ClientRepository;
import org.top.diplom_project_shop.model.repository.OrderRepository;

import java.util.List;
@Service
public class DbDaoOrderImpl implements IDaoOrder {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Order> listAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order add(Order order) {
        Client client = clientRepository.findById(order.getClient().getId()).orElse(null);
        if (client == null)
            return null;
        client.getOrderSet().add(order);
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        Order orderTemp = orderRepository.findById(order.getId()).orElse(null);
        if (orderTemp != null)
            orderTemp.setDescription(orderTemp.getDescription());
        return orderRepository.save(order);
    }

    @Override
    public Order delete(Integer id) {
        Order order = orderRepository.findById(id).orElse(null);
        orderRepository.deleteById(id);
        return order;
    }
}
