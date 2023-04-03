package org.top.diplom_project_shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.diplom_project_shop.model.dao.client.IDaoClient;
import org.top.diplom_project_shop.model.dao.orderproduct.IDaoOrderProduct;
import org.top.diplom_project_shop.model.entity.Client;
import org.top.diplom_project_shop.model.entity.OrderProduct;

import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private IDaoOrderProduct daoOrderProduct;
    @Autowired
    private IDaoClient daoClient;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registration")
    public String index(Model model) {
        model.addAttribute("client", new Client());
        return "registration";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        if (SecurityContextHolder.getContext().getAuthentication() != null)
            request.getSession().invalidate();
        return "redirect:/";
    }
    @GetMapping("/catalog")
    public String catalog() {
        return "catalog";
    }
    @GetMapping("/basket")
    public String basket(Model model) {
        List<OrderProduct> orderProducts = daoOrderProduct.listAll();
        List<Client> clients = daoClient.listAll();
        model.addAttribute("clients",clients);
        model.addAttribute("orderProducts",orderProducts);
        return "basket";
    }

}



