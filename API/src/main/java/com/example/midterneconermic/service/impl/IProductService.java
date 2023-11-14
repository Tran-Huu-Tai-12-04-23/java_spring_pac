package com.example.midterneconermic.service.impl;

import com.example.midterneconermic.exception.NotFoundException;
import com.example.midterneconermic.model.Product;
import com.example.midterneconermic.repository.ProductRepository;
import com.example.midterneconermic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IProductService implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product newProduct) {
        productRepository.save(newProduct);
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        if (updatedProduct.getId() != null) {
            productRepository.save(updatedProduct);
        }else {
            throw new NotFoundException("Product not found");
        }
    }

    @Override
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }
}