package com.example.jsonexdemo.controllers;

import com.example.jsonexdemo.models.dtos.CategorySeedDto;
import com.example.jsonexdemo.models.dtos.ProductInRangeDto;
import com.example.jsonexdemo.models.dtos.ProductSeedDto;
import com.example.jsonexdemo.models.dtos.UserSeedDto;
import com.example.jsonexdemo.models.utils.FileIOUtil;
import com.example.jsonexdemo.services.CategoryService;
import com.example.jsonexdemo.services.ProductService;
import com.example.jsonexdemo.services.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.example.jsonexdemo.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileIOUtil fileIOUtil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedCategories();
        this.seedUsers();
        this.seedProducts();

        this.writeProductInRange();

    }

    private void writeProductInRange() throws IOException {
        List<ProductInRangeDto> dtos = this.productService
                .getAllProductsInRange();

        String json = this.gson.toJson(dtos);

        this.fileIOUtil.write(json, EX_1_OUTPUT);
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] dtos = this.gson
                .fromJson(new FileReader(PRODUCTS_FILE_PATH), ProductSeedDto[].class);

        this.productService.seedProducts(dtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] dtos = this.gson
                .fromJson(new FileReader(USERS_FILE_PATH), UserSeedDto[].class);
        this.userService.seedUsers(dtos);
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] dtos = this.gson
                .fromJson(new FileReader(CATEGORIES_FILE_PATH),
                        CategorySeedDto[].class);

        this.categoryService.seedCategories(dtos);

    }
}
