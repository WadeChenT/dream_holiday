package com.hotel.holiday.dream.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {
    private String id;
    private String type;
    private String title;
    private String description;
    private MultipartFile files;
}
