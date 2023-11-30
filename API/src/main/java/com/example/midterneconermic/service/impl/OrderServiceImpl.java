package com.example.midterneconermic.service.impl;

import com.example.midterneconermic.dto.CartRequest;
import com.example.midterneconermic.exception.NotFoundException;
import com.example.midterneconermic.model.Account;
import com.example.midterneconermic.model.Product;
import com.example.midterneconermic.model.UserCart;
import com.example.midterneconermic.repository.AccountRepository;
import com.example.midterneconermic.repository.ProductRepository;
import com.example.midterneconermic.repository.CartRepository;
import com.example.midterneconermic.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    @Override
    public UserCart getCartByAccount(long accountID) {
        return cartRepository.findByAccount_Id(accountID);
    }

    @Override
    public UserCart addProductToUserCart(CartRequest cartRequest) {
        Optional<Account> accountOptional = accountRepository.findById(cartRequest.getAccountId());

        if( accountOptional.isEmpty()) throw  new RuntimeException("Account not found");

        Optional<Product> productOptional = productRepository.findById(cartRequest.getProductId());

        if( productOptional.isEmpty()) throw  new RuntimeException("Product not found");

        UserCart userCart = cartRepository.findByAccount_Id(cartRequest.getAccountId());

        Account account = accountOptional.get();
        Product product = productOptional.get();

        List<Product> listPro = new ArrayList<>();
        if(userCart == null ) {
            userCart = new UserCart();
            userCart.setAccount(account);
            listPro.add(product);
            userCart.setProductCart(listPro);
            userCart.setTotal(product.getPrice());
            userCart.setQuantity(1);
            return cartRepository.save(userCart);
        }

        listPro = userCart.getProductCart();
        if (!listPro.contains(product)) {
            listPro.add(product);
            userCart.setProductCart(listPro);
            userCart.setTotal(userCart.getTotal() + product.getPrice());
            userCart.setQuantity(userCart.getQuantity() + 1);
            return cartRepository.save(userCart);
        }
        return null;
    }
    @Override
    public UserCart removeItemFromUserCart(Long accountId, Long proId) {
        Optional<Account> accountOptinal = accountRepository.findById(accountId);

        if( accountOptinal.isEmpty() ) {
            throw new NotFoundException("Account not found!");
        }

        UserCart userCart = cartRepository.findByAccount_Id(accountId);

        if (userCart == null) {
            throw new NotFoundException("UserCart is empty");
        }

        List<Product> proList = userCart.getProductCart();

        proList.removeIf(product -> product.getId().equals(proId));

        return cartRepository.save(userCart);
    }
}