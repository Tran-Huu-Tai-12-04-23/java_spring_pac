package com.example.midterneconermic.controller;


import com.example.midterneconermic.dto.ErrorResponse;
import com.example.midterneconermic.model.Product;
import com.example.midterneconermic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app/admin/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{idProduct}")
    public ResponseEntity<?> getProduct(@PathVariable long idProduct) {
        try {
            Product product = productService.getProductById(idProduct);
            return ResponseEntity.ok(product);
        } catch (ResponseStatusException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getReason());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product newProduct) {
        try {
            Product product = productService.addProduct(newProduct);
            return ResponseEntity.ok(product);
        } catch (RuntimeException err) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(err.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable long productId, @RequestBody Product updatedProduct) {
        try {
            // Set the ID of the updated product
            updatedProduct.setId(productId);
            Product product = productService.updateProduct(updatedProduct);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

        @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Product deleted successfully!");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size
    ) {
        try {
            List<Product> products = productService.getAllProducts(page, size);
            return ResponseEntity.ok(products);
        } catch (ResponseStatusException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getReason());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
