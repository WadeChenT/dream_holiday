package com.hotel.holiday.dream.controller;

import com.hotel.holiday.dream.controller.dto.OrderCreateDto;
import com.hotel.holiday.dream.controller.dto.common.ResObject;
import com.hotel.holiday.dream.enums.CodeMsg;
import com.hotel.holiday.dream.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class OrderController {

    private final OrderService orderService;

    @GetMapping({"/getList"})
    public String setupTestSet() {
        return "";
    }

    @PostMapping("/")
    public ResponseEntity<ResObject> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        return ResponseEntity
                .ok()
                .body(ResObject.of(CodeMsg.COMMON_OK)
                        .addResult("orderId", orderService.createOrder(orderCreateDto)));
    }
}
