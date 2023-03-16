package org.top.diplom_project_shop.model.dao.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.diplom_project_shop.model.entity.Catalog;
import org.top.diplom_project_shop.model.repository.CatalogRepository;


import java.util.List;
@Service
public class DbDaoCatalogImpl implements IDaoCatalog {

    @Autowired
    private CatalogRepository catalogRepository;


    @Override
    public List<Catalog> listAll() {
        return (List<Catalog>) catalogRepository.findAll();
    }

    @Override
    public Catalog getById(Integer id) {
        return catalogRepository.findById(id).orElse(null);
    }

    @Override
    public Catalog add(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog update(Catalog catalog) {
        Catalog catalogTemp = catalogRepository.findById(catalog.getId()).orElse(null);
        if (catalogTemp != null)
            catalogTemp.setProductSet(catalogTemp.getProductSet());
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog delete(Integer id) {
        Catalog catalog = catalogRepository.findById(id).orElse(null);
        catalogRepository.deleteById(id);
        return catalog;
    }
}
