package com.example.midterneconermic.controller;


import com.example.midterneconermic.dto.AccountRequest;
import com.example.midterneconermic.dto.ErrorResponse;
import com.example.midterneconermic.dto.JWTAuthenticationResponse;
import com.example.midterneconermic.dto.RefreshTokenRequest;
import com.example.midterneconermic.model.Account;
import com.example.midterneconermic.model.Product;
import com.example.midterneconermic.service.AuthenticationService;
import com.example.midterneconermic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
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

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product newProduct) {
        try {
            productService.addProduct(newProduct);
            return ResponseEntity.ok("Product created successfully!");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{idProduct}")
    public ResponseEntity<?> updateProduct(@PathVariable long idProduct, @RequestBody Product updatedProduct) {
        try {
            // Set the ID of the updated product
            updatedProduct.setId(idProduct);
            productService.updateProduct(updatedProduct);
            return ResponseEntity.ok("Product updated successfully!");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable long idProduct) {
        try {
            productService.deleteProduct(idProduct);
            return ResponseEntity.ok("Product deleted successfully!");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        System.out.println("Hello");
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (ResponseStatusException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getReason());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
