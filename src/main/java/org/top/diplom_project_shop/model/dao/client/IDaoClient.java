package org.top.diplom_project_shop.model.dao.client;

import jakarta.transaction.Transactional;
import org.top.diplom_project_shop.model.entity.Client;

import java.util.List;

public interface IDaoClient  {
    @Transactional
    List<Client> listAll();
    @Transactional
    Client getById(Integer id);

    @Transactional
    Client add(Client client);

    @Transactional
    Client update(Client client);

    @Transactional
    Client delete(Integer id);
    @Transactional
    Client getClientByLogin(String login);

}
