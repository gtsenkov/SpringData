package com.example.jsonexdemo.services;

import com.example.jsonexdemo.models.dtos.ProductInRangeDto;
import com.example.jsonexdemo.models.dtos.ProductSeedDto;

import java.util.List;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDto);

    List<ProductInRangeDto> getAllProductsInRange();
}
