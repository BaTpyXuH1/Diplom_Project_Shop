package org.top.diplom_project_shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.top.diplom_project_shop.model.dao.client.IDaoClient;
import org.top.diplom_project_shop.model.dao.order.IDaoOrder;
import org.top.diplom_project_shop.model.entity.Client;
import org.top.diplom_project_shop.model.entity.Order;

import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private IDaoClient daoClient;
    @Autowired
    private IDaoOrder daoOrder;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @Transactional
    @GetMapping("/isPaid")
    public String isPaid(@RequestParam Integer orderId,Authentication auth) {
       Order order = daoOrder.getById(orderId);
       order.pay();
       Order orderPay = new Order(order.getDescription(),daoClient.getClientByLogin(auth.getName()));
       orderPay = daoOrder.add(orderPay);
        return "isPaid";
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
    public String basket(Model model, Authentication auth) {
        Client client = daoClient.getClientByLogin(auth.getName());
        for(Order pay : client.getOrderSet()){
            if(!pay.isPaid()){
                model.addAttribute("order",pay);
            }
        }
        return "basket";
    }
}



