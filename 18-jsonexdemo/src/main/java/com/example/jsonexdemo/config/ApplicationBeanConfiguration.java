package com.example.jsonexdemo.config;

import com.example.jsonexdemo.models.utils.ValidationUtilImpl;
import com.example.jsonexdemo.models.utils.ValidationUtill;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidationUtill validationUtill() {
        return new ValidationUtilImpl();
    }
}
