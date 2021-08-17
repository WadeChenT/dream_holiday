package com.hotel.holiday.dream.controller;

import com.hotel.holiday.dream.controller.dto.OrderCreateDto;
import com.hotel.holiday.dream.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping({"/getList"})
    public String setupTestSet() {
        return "";
    }

    @PostMapping("/")
    public String createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        orderService.createOrder(orderCreateDto);
        return "Success!!";
    }
}
