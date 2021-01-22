package com.example.jsonexdemo.services;

import com.example.jsonexdemo.models.dtos.CategorySeedDto;
import com.example.jsonexdemo.models.entities.Category;
import com.example.jsonexdemo.models.utils.ValidationUtill;
import com.example.jsonexdemo.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtill validationUtill;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtill validationUtill) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtill = validationUtill;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(categorySeedDtos)
                .forEach(categorySeedDto -> {
                    if (this.validationUtill.isValid(categorySeedDto)) {
                        Category category = this.modelMapper
                                .map(categorySeedDto, Category.class);
                        this.categoryRepository
                                .saveAndFlush(category);
                    } else {
                        this.validationUtill
                                .violations(categorySeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<Category> getRandomCategories() {
        Random random = new Random();
        List<Category> resultList = new ArrayList<>();
        int randomCounter = random.nextInt(3) + 1;

        for (int i = 0; i < randomCounter; i++) {
            long randomId = random
                    .nextInt((int) (this.categoryRepository.count())) + 1;

            resultList.add(this.categoryRepository.getOne(randomId));
        }
        return resultList;
    }
}
