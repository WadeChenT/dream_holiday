package com.hotel.holiday.dream.service;

import com.hotel.holiday.dream.controller.dto.OrderCreateDto;
import com.hotel.holiday.dream.entity.OrderList;

import java.util.List;

public interface OrderService {
    void createOrder(OrderCreateDto orderCreateDto);

    OrderList get(String id);
    List<OrderList> getList();
    OrderList update();
    void delete(String id);
}
