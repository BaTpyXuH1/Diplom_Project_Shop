package org.top.diplom_project_shop.model.dao.client;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.top.diplom_project_shop.model.entity.Client;
import org.top.diplom_project_shop.model.entity.Order;
import org.top.diplom_project_shop.model.repository.ClientRepository;
import org.top.diplom_project_shop.model.repository.OrderRepository;

import java.util.List;

@Service
public class DbDaoClientImpl implements IDaoClient {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Override
    @Transactional
    public List<Client> listAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client getById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public Client add(Client client) {
        client.setPassword(encoder.encode(client.getPassword()));
        if (client.getLogin().equalsIgnoreCase("admin"))
            client.setRole("ADMIN");
        else
            client.setRole("USER");
        client = clientRepository.save(client);
        Order order = new Order();
        order.setClient(client);
        order = orderRepository.save(order);
        client.getOrderSet().add(order);
        return clientRepository.save(client);
    }


    @Override
    @Transactional
    public Client update(Client client) {
        Client clientTemp = clientRepository.findById(client.getId()).orElse(null);
        if (clientTemp != null)
            clientTemp.setClientName(client.getClientName());
        return clientTemp;
    }


    @Override
    @Transactional
    public Client delete(Integer id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null)
            clientRepository.deleteById(id);
        return client;
    }

    @Override
    @Transactional
    public Client getClientByLogin(String login) {
        return clientRepository.findByLogin(login);
    }

//!!!!!!!!!!!!!!!!
    @Override
    public Order getBasket(Integer id) {
        Client client = clientRepository.findById(id).get();
        for (Order order : client.getOrderSet())
            if (!order.isPaid())
                return order;
        return null;
    }
}
