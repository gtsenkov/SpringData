package com.example.jsonexdemo.services;

import com.example.jsonexdemo.models.dtos.CategorySeedDto;
import com.example.jsonexdemo.models.entities.Category;

import java.util.List;

public interface CategoryService {
   void seedCategories(CategorySeedDto[] categorySeedDtos);

   List<Category> getRandomCategories();
}
