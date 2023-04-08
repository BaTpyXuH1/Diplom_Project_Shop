package org.top.diplom_project_shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.diplom_project_shop.model.dao.client.IDaoClient;
import org.top.diplom_project_shop.model.entity.Client;

import java.util.List;


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
        client.setClientName(username);
        if (client.getLogin().equals("admin"))
            client.setRole("ADMIN");
        else
            client.setRole("USER");
        daoClient.add(client);
        return "/index";
    }

    @GetMapping("/")
    public String listALl(Model model) {
        List<Client> clients = daoClient.listAll();
        model.addAttribute("clients",clients);
        return "/client/client-list";
    }

    @GetMapping("/add/")
    public String getAddForm(Model model){
        Client client = new Client();
        model.addAttribute("client",client);
        return "/client/client-form";

    }
    @PostMapping("/add/")
    public String addNewClient(Client client, RedirectAttributes ra){
        Client addedClient = daoClient.add(client);
        ra.addFlashAttribute("goodMsg","Client " + addedClient + "added");
        return "redirect:/client/";

    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") Integer clientId){
        daoClient.delete(clientId);
        return "redirect:/client/";
    }
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable("id") Integer clientId, Model model){
        Client client = daoClient.getById(clientId);
        model.addAttribute("client",client);
        return "/client/client-update";

    }
    @PostMapping("/update/")
    public String updateClient(Client client){
        daoClient.update(client);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer clientId, Model model){
        Client client = daoClient.getById(clientId);
        model.addAttribute("client",client);
        return "/client/client-detail";
    }
}
