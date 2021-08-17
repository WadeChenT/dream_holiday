package com.hotel.holiday.dream.controller;

import com.hotel.holiday.dream.controller.dto.ProductCreateDto;
import com.hotel.holiday.dream.controller.dto.ProductDto;
import com.hotel.holiday.dream.controller.dto.common.ResObject;
import com.hotel.holiday.dream.enums.CodeMsg;
import com.hotel.holiday.dream.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping({"/"})
    public ResponseEntity<ResObject> getProductLists() {
        return ResponseEntity
                    .ok()
                    .body(ResObject.of(CodeMsg.COMMON_OK)
                                    .addResult("productList", productService.getList()));
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<ResObject> getProduct(@PathVariable String productId) {
        return ResponseEntity
                    .ok()
                    .body(ResObject.of(CodeMsg.COMMON_OK)
                                    .addResult("product", productService.get(productId)));
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<ResObject> createProduct(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
                .ok()
                .body(ResObject.of(CodeMsg.COMMON_OK));
    }

    @PostMapping("/create")
    public ResponseEntity<ResObject> createProduct(@ModelAttribute ProductCreateDto productCreateDto) {
        productService.create(productCreateDto);
        return ResponseEntity
                    .ok()
                    .body(ResObject.of(CodeMsg.COMMON_OK));
    }

    @PostMapping("/update")
    public ResponseEntity<ResObject> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity
                    .ok()
                    .body(ResObject.of(CodeMsg.COMMON_OK));
    }

    @PostMapping("/delete")
    public ResponseEntity<ResObject> deleteProduct(@RequestBody String productId) {
        return ResponseEntity
                    .ok()
                    .body(ResObject.of(CodeMsg.COMMON_OK));
    }
}
