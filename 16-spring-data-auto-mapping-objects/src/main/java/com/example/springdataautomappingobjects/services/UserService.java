package com.example.springdataautomappingobjects.services;

import com.example.springdataautomappingobjects.domain.dtos.UserLoginDto;
import com.example.springdataautomappingobjects.domain.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean isLoggedUserAdmin();
}
