package org.top.diplom_project_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.diplom_project_shop.model.dao.order.IDaoOrder;
import org.top.diplom_project_shop.model.dao.product.DbDaoProductImpl;
import org.top.diplom_project_shop.model.dao.product.IDaoProduct;
import org.top.diplom_project_shop.model.entity.Order;
import org.top.diplom_project_shop.model.entity.Product;
import org.top.diplom_project_shop.model.repository.ProductRepository;


import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private IDaoProduct daoProduct;
    @Autowired
    private IDaoOrder daoOrder;


    @GetMapping("/")
    public String listAll(Model model, Authentication auth) {
        List<Product> products = daoProduct.listAll();
//        if (auth != null)
//            model.addAttribute("isAuth", auth.getAuthorities().toString().contains("ADMIN"));
//        else
//            model.addAttribute("isAuth",false);
        model.addAttribute("product", daoProduct.listAll());
        model.addAttribute("products", products);
        return "/product/product-list";
    }

    @GetMapping("/add/")
    public String getAddProductForm(Model model) {
        Product product = new Product();
        List<Order> orders = daoOrder.listAll();
        model.addAttribute("product", product);
        model.addAttribute("orders", orders);
        return "/product/product-form";
    }

    @PostMapping("/add/")
    public String saveProduct(Product product, @RequestParam("previewImageData") MultipartFile imageData,
                              RedirectAttributes ra) throws IOException {
        String imageDataAsString = Base64
                .getEncoder()
                .encodeToString(imageData.getBytes());
        product.setPreviewImage(imageDataAsString);
        Product addedProduct = daoProduct.add(product);   // save(product)
        ra.addFlashAttribute("goodMsg", "Товар " + addedProduct + " добавлен");
        return "redirect:/product/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer productId) {
        daoProduct.delete(productId);
        return "redirect:/product/";
    }

    @GetMapping("/update/{id}")
    public String getUpdateProduct(@PathVariable("id") Integer productId, Model model) {
        Product product = daoProduct.getById(productId);
        model.addAttribute("product", product);
        return "/product/product-update";
    }

    @PostMapping("/update/")
    public String updateProduct(Product product) {
        daoProduct.upPrice(product);
        daoProduct.update(product);
        return "redirect:/product/";
    }

    @GetMapping("/detail/{id}")
    public String detailProduct(@PathVariable("id") Integer productId, Model model) {
        Product product = daoProduct.getById(productId);
        model.addAttribute("product", product);
        return "/product/product-detail";
    }

}
