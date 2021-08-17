package com.hotel.holiday.dream.service.Impl;

import com.hotel.holiday.dream.controller.dto.ProductCreateDto;
import com.hotel.holiday.dream.controller.dto.ProductDto;
import com.hotel.holiday.dream.entity.Product;
import com.hotel.holiday.dream.exception.DreamHotelException;
import com.hotel.holiday.dream.repo.ProductRepository;
import com.hotel.holiday.dream.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product get(String id) {
        return productRepository.findById(id)
                    .orElseThrow(() -> DreamHotelException.occur(DreamHotelException.ErrorEnum.NOT_FOUND));
    }

    @Override
    public List<Product> getList() {
        return productRepository.findAll();
    }

    @Override
    public void create(ProductCreateDto productCreateDto) {
        Product entity = new Product();
        try {
            entity.setFiles(productCreateDto.getFiles().getBytes());
            entity.setTitle(productCreateDto.getTitle());
            entity.setDescription(productCreateDto.getDescription());
            entity.setType(productCreateDto.getType());
        } catch (Exception e) {
            e.printStackTrace();
            throw DreamHotelException.occur(DreamHotelException.ErrorEnum.COMMON_ERROR);
        }

        productRepository.save(entity);
    }

    @Override
    public Product update(ProductDto productDto) {
        Product product = this.get(productDto.getId());
        try {
            product.setFiles(productDto.getFiles().getBytes());
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setType(productDto.getType());
        } catch (IOException e) {
            e.printStackTrace();
            throw DreamHotelException.occur(DreamHotelException.ErrorEnum.COMMON_ERROR);
        }

        return product;
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
