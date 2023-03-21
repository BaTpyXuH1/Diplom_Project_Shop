package org.top.diplom_project_shop.model.dao.orderproduct;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.diplom_project_shop.model.entity.Order;
import org.top.diplom_project_shop.model.entity.OrderProduct;
import org.top.diplom_project_shop.model.entity.Product;
import org.top.diplom_project_shop.model.repository.OrderProductRepository;
import org.top.diplom_project_shop.model.repository.OrderRepository;
import org.top.diplom_project_shop.model.repository.ProductRepository;

import java.util.List;
@Service
public class DbDaoOrderProductImpl implements IDaoOrderProduct{
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    @Transactional
    public List<OrderProduct> listAll() {
        return (List<OrderProduct>) orderProductRepository.findAll();

    }

    @Override
    @Transactional
    public OrderProduct getById(Integer id) {
        return orderProductRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public OrderProduct add(OrderProduct orderProduct) {
        Order order = orderRepository.findById(orderProduct.getOrder().getId()).orElse(null);
        if (order == null)
            return null;
        Product product = productRepository.findById(orderProduct.getProduct().getId()).orElse(null);
        if (product == null)
            return null;
        return orderProductRepository.save(orderProduct);
    }

    @Override
    @Transactional
    public OrderProduct update(OrderProduct orderProduct) {
        OrderProduct orderProductTemp = orderProductRepository.findById(orderProduct.getId()).orElse(null);
        if (orderProductTemp != null)
            orderProductTemp.setQuantityProduct(orderProductTemp.getQuantityProduct());
        return orderProductRepository.save(orderProduct);
    }

    @Override
    @Transactional
    public OrderProduct delete(Integer id) {
        OrderProduct orderProduct = orderProductRepository.findById(id).orElse(null);
        orderProductRepository.deleteById(id);
        return orderProduct;
    }
}
