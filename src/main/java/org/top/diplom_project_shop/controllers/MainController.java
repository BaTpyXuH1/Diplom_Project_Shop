package org.top.diplom_project_shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.top.diplom_project_shop.model.entity.Client;


import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping("/")
    public String index(Model model, Authentication auth) {
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        if (auth == null)
//            model.addAttribute("isAuth", null);
//        else {
//            authorities.addAll(auth.getAuthorities());
//            model.addAttribute("isAuth", authorities.get(0).getAuthority().substring(5));
//        }
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
}

