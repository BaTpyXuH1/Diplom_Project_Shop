package org.top.diplom_project_shop.model.dao.client;

import org.top.diplom_project_shop.model.entity.Client;

import java.util.List;

public interface IDaoClient  {
    List<Client> listAll();

    Client getById(Integer id);


    Client add(Client client);


    Client update(Client client);


    Client delete(Integer id);

    Client getClientByLogin(String login);

}
