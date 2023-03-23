package org.top.diplom_project_shop.model.repository;


import org.springframework.data.repository.CrudRepository;
import org.top.diplom_project_shop.model.entity.Basket;

public interface BasketRepository extends CrudRepository<Basket,Integer> {

    Basket findBasketByClientLogin(String login);
}