package org.top.diplom_project_shop.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.diplom_project_shop.model.entity.Client;

public interface ClientRepository extends CrudRepository<Client,Integer> {
    Client findByLogin(String login);
}
