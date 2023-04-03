package org.top.diplom_project_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.diplom_project_shop.model.dao.product.IDaoProduct;
import org.top.diplom_project_shop.model.entity.Client;
import org.top.diplom_project_shop.model.entity.Order;
import org.top.diplom_project_shop.model.entity.OrderProduct;
import org.top.diplom_project_shop.model.entity.Product;
import org.top.diplom_project_shop.model.repository.ClientRepository;
import org.top.diplom_project_shop.model.repository.OrderProductRepository;
import org.top.diplom_project_shop.model.repository.OrderRepository;
import org.top.diplom_project_shop.model.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;


    @GetMapping("/addProduct/{id}")
    public String addProduct(Model model, @PathVariable("id") Integer id, Authentication auth) {
        if (auth != null) {
            Client client = clientRepository.findByLogin(auth.getName());
            Product product = productRepository.findById(id).orElse(null);
//            client.getBasket().getOrderProductSet();
            OrderProduct orderProduct = new OrderProduct(1,product,client.getBasket());
            orderProductRepository.save(orderProduct);






        }


        return "/goods/bicycles";
    }
}