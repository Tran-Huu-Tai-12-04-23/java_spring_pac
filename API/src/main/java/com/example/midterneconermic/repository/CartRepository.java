package com.example.midterneconermic.repository;

import com.example.midterneconermic.model.Product;
import com.example.midterneconermic.model.UserCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    UserCart findByAccount_Id(Long accountId);
}