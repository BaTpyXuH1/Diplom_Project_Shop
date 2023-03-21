package org.top.diplom_project_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.diplom_project_shop.model.dao.client.IDaoClient;
import org.top.diplom_project_shop.model.dao.order.DbDaoOrderImpl;
import org.top.diplom_project_shop.model.entity.Client;
import org.top.diplom_project_shop.model.entity.Order;

import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private DbDaoOrderImpl daoOrder;
    @Autowired
    private IDaoClient daoClient;


    @GetMapping("/")
    public String listAll(Model model, Authentication auth) {
        List<Order> orders = daoOrder.listAll();
        if (auth != null)
            model.addAttribute("isAuth", auth.getAuthorities().toString().contains("ADMIN"));
        else
            model.addAttribute("isAUth",false);
        model.addAttribute("orders", orders);
        return "/order/order-list";
    }

    // Получение form добавления нового заказа
    @GetMapping("/add/")
    public String getOrderForm(Model model) {
        Order order = new Order();
        List<Client> clients = daoClient.listAll();
        model.addAttribute("order", order);
        model.addAttribute("clients", clients);
        return "/order/order-form";
    }

    @PostMapping("/add/")
    public String saveOrder(Order order, RedirectAttributes ra) {
        Order addedOrder = daoOrder.add(order);
        ra.addFlashAttribute("goodMsg", "Заказ " + addedOrder + " добавлен");
        return "redirect:/order/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer orderId) {
        daoOrder.delete(orderId);
        return "redirect:/order/";
    }

    @GetMapping("/update/{id}")
    public String getUpdateOrder(@PathVariable("id") Integer orderId, Model model) {
        Order order = daoOrder.getById(orderId);
        model.addAttribute("order", order);
        return "/order/order-update";
    }

    @PostMapping("/update/")
    public String updatedOrder(Order order) {
        daoOrder.update(order);
        return "redirect:/order/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer orderId, Model model) {
        Order order = daoOrder.getById(orderId);
        model.addAttribute("order", order);
        return "/order/order-detail";
    }
}
