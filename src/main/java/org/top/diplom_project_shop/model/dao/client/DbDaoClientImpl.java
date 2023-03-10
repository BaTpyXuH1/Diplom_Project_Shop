package org.top.diplom_project_shop.model.dao.client;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.top.diplom_project_shop.model.entity.Client;
import org.top.diplom_project_shop.model.repository.ClientRepository;
import java.util.List;

@Service
public class DbDaoClientImpl implements IDaoClient {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ClientRepository clientRepository;



    @Override
    public List<Client> listAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
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
        Client item = clientRepository.findById(id).orElse(null);
        clientRepository.deleteById(id);
        return item;
    }

    @Override
    public Client getClientByLogin(String login) {
        return clientRepository.findByLogin(login);
    }
}
