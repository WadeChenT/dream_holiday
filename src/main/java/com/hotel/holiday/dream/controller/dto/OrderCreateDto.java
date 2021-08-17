package com.hotel.holiday.dream.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderCreateDto {
    String userId;
    String email;
    String productId;
    LocalDateTime startAt;
    LocalDateTime endAt;
}
