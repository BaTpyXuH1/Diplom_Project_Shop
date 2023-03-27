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
import org.top.diplom_project_shop.model.dao.order.DbDaoOrderImpl;
import org.top.diplom_project_shop.model.dao.orderproduct.DbDaoOrderProductImpl;
import org.top.diplom_project_shop.model.dao.product.DbDaoProductImpl;
import org.top.diplom_project_shop.model.entity.Order;
import org.top.diplom_project_shop.model.entity.OrderProduct;
import org.top.diplom_project_shop.model.entity.Product;

import java.util.List;


@Controller
@RequestMapping("/orderProduct")
public class OrderProductController {
    @Autowired
    private DbDaoOrderProductImpl daoOrderProduct;

    @Autowired
    private DbDaoOrderImpl daoOrder;

    @Autowired
    private DbDaoProductImpl daoProduct;



    @GetMapping("/")
    public String listAll(Model model, Authentication auth) {
        List<OrderProduct> orderProducts = daoOrderProduct.listAll();
        if (auth != null)
            model.addAttribute("isAuth", auth.getAuthorities().toString().contains("ADMIN"));
        else
            model.addAttribute("isAuth",false);
        model.addAttribute("orderProducts", orderProducts);
        return "/orderProduct/orderProduct-list";
    }

    @GetMapping("/add/")
    public String getOrderProductForm(Model model) {
        OrderProduct orderProduct = new OrderProduct();
        List<Order> orders = daoOrder.listAll();
        List<Product> products = daoProduct.listAll();
        model.addAttribute("orderProduct", orderProduct);
        model.addAttribute("orders", orders);
        model.addAttribute("products", products);
        return "/orderProduct/orderProduct-form";
    }

    @PostMapping("/add/")
    public String saveOrderProduct(OrderProduct orderProduct, RedirectAttributes ra) {
        OrderProduct addOrderProduct = daoOrderProduct.add(orderProduct);
        ra.addFlashAttribute("goodMsg", "Заказ " + addOrderProduct + " добавлен");
        return "redirect:/orderProduct/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderProduct(@PathVariable("id") Integer orderProductId) {
        daoOrderProduct.delete(orderProductId);
        return "redirect:/orderProduct/";
    }

    @GetMapping("/update/{id}")
    public String getUpdateOrderProduct(@PathVariable("id") Integer orderProductId, Model model) {
        OrderProduct orderProduct = daoOrderProduct.getById(orderProductId);
        model.addAttribute("orderProduct", orderProduct);
        return "/orderProduct/orderProduct-update";
    }

    @PostMapping("/update/")
    public String updateOrderProduct(OrderProduct orderProduct) {
        daoOrderProduct.update(orderProduct);
        return "redirect:/orderProduct/";
    }

    @GetMapping("/detail/{id}")
    public String detailOrderProduct(@PathVariable("id") Integer orderProductId, Model model) {
        OrderProduct orderProduct = daoOrderProduct.getById(orderProductId);
        model.addAttribute("orderProduct", orderProduct);
        return "/orderProduct/orderProduct-detail";
    }


}
