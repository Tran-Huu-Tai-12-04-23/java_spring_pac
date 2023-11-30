package com.example.midterneconermic.controller;


import com.example.midterneconermic.dto.CartRequest;
import com.example.midterneconermic.dto.ErrorResponse;
import com.example.midterneconermic.dto.OrderRequest;
import com.example.midterneconermic.model.Product;
import com.example.midterneconermic.model.UserCart;
import com.example.midterneconermic.model.UserOrder;
import com.example.midterneconermic.service.CartService;
import com.example.midterneconermic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/app/user/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @GetMapping("/{accountId}")
    public ResponseEntity<?> getALlOrderByAccount(@PathVariable Long accountId) {
        try {
            List<UserOrder> userOrderList = orderService.getALl(accountId);
            return ResponseEntity.ok(userOrderList);
        }  catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Có lỗi xảy ra trong quá trình xử lý yêu cầu.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PostMapping()
    public ResponseEntity<?> addToOrder(@RequestBody OrderRequest orderRequest) {
        try {
            UserOrder userOrder = orderService.addOrder(orderRequest);
            if( userOrder != null ) {
                return ResponseEntity.ok(userOrder);
            }else {
                return ResponseEntity.badRequest().body("Order failed!");
            }
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        try {
            Boolean orderRes = orderService.cancelOrder(orderId);
            if( orderRes ) {
                return ResponseEntity.ok("Cancel order successfully!");
            }else {
                return ResponseEntity.badRequest().body("Cancel order failed!");
            }
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


}
