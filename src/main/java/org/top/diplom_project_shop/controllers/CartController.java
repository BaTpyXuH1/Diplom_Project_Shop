package org.top.diplom_project_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.diplom_project_shop.model.dao.client.IDaoClient;
import org.top.diplom_project_shop.model.dao.orderproduct.IDaoOrderProduct;
import org.top.diplom_project_shop.model.dao.product.IDaoProduct;
import org.top.diplom_project_shop.model.entity.Client;
import org.top.diplom_project_shop.model.entity.OrderProduct;
import org.top.diplom_project_shop.model.entity.Product;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private IDaoProduct daoProduct;
    @Autowired
    private IDaoClient daoClient;
    @Autowired
    private IDaoOrderProduct daoOrderProduct;


    @GetMapping("/addProduct/{id}")
    public String addProduct(Model model, @PathVariable("id") Integer id, Authentication auth) {
        List<Product> products = daoProduct.listAll();
        model.addAttribute("products", products);
        if (auth != null) {
            Client client = daoClient.getClientByLogin(auth.getName());
            Product product = daoProduct.getById(id);
            OrderProduct orderProduct = new OrderProduct(1, product, client.getBasket());
            daoOrderProduct.add(orderProduct);
        }
        return "redirect:/basket";
    }
}