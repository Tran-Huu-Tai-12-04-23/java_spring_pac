package com.example.midterneconermic.controller;


import com.example.midterneconermic.dto.CartRequest;
import com.example.midterneconermic.dto.ErrorResponse;
import com.example.midterneconermic.model.Product;
import com.example.midterneconermic.model.UserCart;
import com.example.midterneconermic.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/app/user/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping()
    public ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest) {
        try {
            UserCart userCart = cartService.addProductToUserCart(cartRequest);
            if( userCart != null ) {
                return ResponseEntity.ok(userCart);
            }else {
                return ResponseEntity.badRequest().body("Add product to cart failed!");
            }
        } catch (ResponseStatusException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getReason());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @GetMapping()
    public ResponseEntity<?> getUserCart(@RequestParam Long accountId) {
        try {
            UserCart userCart = cartService.getCartByAccount(accountId);
            return ResponseEntity.ok(userCart);
        } catch (ResponseStatusException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getReason());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> removeProFromCart(@RequestParam Long accountId, Long productId) {
        try {
            UserCart userCart = cartService.removeItemFromUserCart(accountId,productId);
            return ResponseEntity.ok(userCart);
        } catch (ResponseStatusException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getReason());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
