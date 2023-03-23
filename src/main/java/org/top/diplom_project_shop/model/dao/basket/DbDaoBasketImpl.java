package org.top.diplom_project_shop.model.dao.basket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.diplom_project_shop.model.entity.Basket;
import org.top.diplom_project_shop.model.repository.BasketRepository;

import java.util.List;

@Service
public class DbDaoBasketImpl implements IDaoBasket {
    @Autowired
    private BasketRepository basketRepository;

    @Override
    public List<Basket> listAll() {
        return (List<Basket>) basketRepository.findAll();
    }

    @Override
    public Basket getById(Integer id) {
        return basketRepository.findById(id).orElse(null);
    }

    @Override
    public Basket add(Basket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public Basket update(Basket basket) {
        Basket basketTemp = basketRepository.findById(basket.getId()).orElse(null);
        if (basketTemp == null)
            return null;
        basketTemp.setDescription(basket.getDescription());
        return basketRepository.save(basketTemp);
    }

    @Override
    public Basket delete(Integer id) {
        Basket basket = basketRepository.findById(id).orElse(null);
        if (basket != null)
            basketRepository.deleteById(id);
        return basket;
    }

    @Override
    public Basket findByLogin(String login) {
        return basketRepository.findBasketByClientLogin(login);
    }
}