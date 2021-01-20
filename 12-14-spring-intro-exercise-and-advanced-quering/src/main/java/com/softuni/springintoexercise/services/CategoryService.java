package com.softuni.springintoexercise.services;

import com.softuni.springintoexercise.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    Category getCategoryById(Long id);
}
