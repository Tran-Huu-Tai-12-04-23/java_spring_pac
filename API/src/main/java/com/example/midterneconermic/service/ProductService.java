package com.example.midterneconermic.service;

import com.example.midterneconermic.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long productId);

    List<Product> getAllProducts();

    void addProduct(Product newProduct);

    void updateProduct(Product updatedProduct);

    void deleteProduct(long productId);

}
