package com.hotel.holiday.dream.repo;

import com.hotel.holiday.dream.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByType(String types);
}
