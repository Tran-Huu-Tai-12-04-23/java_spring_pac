package com.example.midterneconermic.service;

import com.example.midterneconermic.dto.CartRequest;
import com.example.midterneconermic.model.UserCart;
import com.example.midterneconermic.model.Product;

public interface CartService {
    UserCart getCartByAccount(long accountID);

    Product addProductToUserCart(CartRequest cartRequest);

    UserCart removeItemFromUserCart(Long accountId, Long proId);

}
