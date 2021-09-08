package com.hotel.holiday.dream.service;

import com.hotel.holiday.dream.controller.dto.ProductCreateDto;
import com.hotel.holiday.dream.controller.dto.ProductDto;
import com.hotel.holiday.dream.entity.Product;

import java.util.List;

public interface ProductService {
    Product get(String id);
    List<Product> getList();
    List<Product> getList(String type);
    void create(ProductCreateDto productCreateDto);
    Product update(ProductDto productDto);
    void delete(String id);
}
