package org.top.diplom_project_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.diplom_project_shop.model.dao.product.IDaoProduct;
import org.top.diplom_project_shop.model.entity.Product;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IDaoProduct daoProduct;

    @GetMapping("/bicycles")
    public String bicycles(Model model){
        List<Product> products = daoProduct.listAll();
        model.addAttribute("products",products);
        return "/goods/bicycles";
    }

    @GetMapping("/others")
    public String others(Model model) {
        List<Product> products = daoProduct.listAll();
        model.addAttribute("products",products);
        return "/goods/others";
    }@GetMapping("/simulators")
    public String simulators(Model model) {
        List<Product> products = daoProduct.listAll();
        model.addAttribute("products",products);
        return "/goods/simulators";
    }@GetMapping("/snowboards")
    public String snowboards(Model model) {
        List<Product> products = daoProduct.listAll();
        model.addAttribute("products",products);
        return "/goods/snowboards";
    }@GetMapping("/swimming")
    public String swimming(Model model) {
        List<Product> products = daoProduct.listAll();
        model.addAttribute("products",products);
        return "/goods/swimming";
    }
    @GetMapping("/tennis")
    public String tennis(Model model) {
        List<Product> products = daoProduct.listAll();
        model.addAttribute("products",products);
        return "/goods/tennis";
    }
}
