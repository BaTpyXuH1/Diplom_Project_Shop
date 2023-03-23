package org.top.diplom_project_shop.model.dao.basket;

import jakarta.transaction.Transactional;
import org.top.diplom_project_shop.model.entity.Basket;

import java.util.List;

public interface IDaoBasket {
    @Transactional
    List<Basket> listAll();

    @Transactional
    Basket getById(Integer id);

    @Transactional
    Basket add(Basket basket);

    @Transactional
    Basket update(Basket basket);

    @Transactional
    Basket delete(Integer id);
    Basket findByLogin(String login);
}