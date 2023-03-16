package org.top.diplom_project_shop.model.dao.catalog;

import org.top.diplom_project_shop.model.entity.Catalog;


import java.util.List;

public interface IDaoCatalog {
    List<Catalog> listAll();

    Catalog getById(Integer id);


    Catalog add(Catalog catalog);


    Catalog update(Catalog catalog);


    Catalog delete(Integer id);
}
