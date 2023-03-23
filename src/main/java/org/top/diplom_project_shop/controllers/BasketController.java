package org.top.diplom_project_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.diplom_project_shop.model.dao.basket.IDaoBasket;
import org.top.diplom_project_shop.model.dao.orderproduct.IDaoOrderProduct;
import org.top.diplom_project_shop.model.entity.Basket;
import org.top.diplom_project_shop.model.entity.OrderProduct;


import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private IDaoBasket daoBasket;
    @Autowired
    private IDaoOrderProduct daoOrderProduct;


    @GetMapping("/")
    public String listAll(Model model){
        List<Basket> baskets = daoBasket.listAll();
        model.addAttribute("basket",baskets);
        return "/basket";
    }
    @GetMapping("/add/")
    public String getBasketForm(Model model) {
//        Basket basket = new Basket();
        List<OrderProduct> orderProducts = daoOrderProduct.listAll();
//        model.addAttribute("basket",basket);
        model.addAttribute("OrderProducts", orderProducts);
        return "/basket";
    }
    @PostMapping("/add"+"{id}")
    public String saveBasket(@PathVariable("id") Integer productId, RedirectAttributes ra) {
        String login = (SecurityContextHolder.getContext().getAuthentication().getName());
        Basket basket = daoBasket.findByLogin(login);
        System.out.println(basket.getId());
        return "redirect:/add";
    }
    @GetMapping("/delete/{id}")
    public String deleteBasket(@PathVariable("id") Integer basketId) {
        daoBasket.delete(basketId);
        return "redirect:/basket/";
    }
    @GetMapping("/update/{id}")
    public String getUpdateBasket(@PathVariable("id") Integer basketId, Model model) {
        Basket basket = daoBasket.getById(basketId);
        model.addAttribute("basket",basket);
        return "/basket";
    }

    @PostMapping("/update/")
    public String updateBasket(Basket basket) {
        daoBasket.update(basket);
        return "redirect:/basket/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer basketId, Model model) {
        Basket basket = daoBasket.getById(basketId);
        model.addAttribute("basket",basket);
        return "/basket";
    }

}