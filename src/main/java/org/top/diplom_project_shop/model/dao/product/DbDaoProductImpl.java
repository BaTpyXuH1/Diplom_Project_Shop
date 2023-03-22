package org.top.diplom_project_shop.model.dao.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.diplom_project_shop.model.entity.Product;
import org.top.diplom_project_shop.model.repository.ProductRepository;

import java.util.List;

@Service
public class DbDaoProductImpl implements IDaoProduct {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> listAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product upPrice(Product product) {
        Product productTemp = productRepository.findById(product.getId()).orElse(null);
        if (productTemp != null)
            product.setProductPrice(product.getProductPrice());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        Product productTemp = productRepository.findById(product.getId()).orElse(null);
        if (productTemp != null)
            productTemp.setProductName(productTemp.getProductName());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product delete(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        productRepository.deleteById(id);
        return product;
    }
}
