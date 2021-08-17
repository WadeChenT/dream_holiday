package com.hotel.holiday.dream.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreateDto {
    String type;
    String title;
    String description;
    MultipartFile files;
}
