package com.example.midterneconermic.repository;

import com.example.midterneconermic.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<UserOrder, Long> {
}