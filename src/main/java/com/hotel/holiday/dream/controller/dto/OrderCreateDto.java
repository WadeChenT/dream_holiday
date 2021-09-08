package com.hotel.holiday.dream.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderCreateDto {
    String id;
    String userId;
    String email;
    String productId;
    Date startAt;
    Date endAt;
    String price;
    String count;
    String remark;
    String type;
}
