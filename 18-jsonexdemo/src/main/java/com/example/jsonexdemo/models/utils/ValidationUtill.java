package com.example.jsonexdemo.models.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtill {

    <T> boolean isValid(T entity);

    <T> Set<ConstraintViolation<T>> violations(T entity);
}
