package org.top.diplom_project_shop.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping("/")
    public String index(Model model, Authentication auth) {
        System.out.println(auth);
        model.addAttribute("isAuth",null);
        return "/index";
    }
}
