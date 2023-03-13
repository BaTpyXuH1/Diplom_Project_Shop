package org.top.diplom_project_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.top.diplom_project_shop.model.dao.client.IDaoClient;
import org.top.diplom_project_shop.model.entity.Client;


@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private IDaoClient daoClient;

    @PostMapping("/add")
    public String addClient(Model model, @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String repeat_password) {
        if (!password.equals(repeat_password)) {
            model.addAttribute("error", "Пароли не совпадают");
            return "registration";
        }
        if (daoClient.getClientByLogin(username) != null) {
            model.addAttribute("error", "Такой логин уже зарегистрирован");
            return "registration";
        }
        Client client = new Client(username, password);
        if (client.getLogin().equals("admin"))
            client.setRole("ADMIN");
        else
            client.setRole("USER");
        System.out.println(client);
        daoClient.add(client);
        return "index";

    }
}
