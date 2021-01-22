package com.example.jsonexdemo.services;


import com.example.jsonexdemo.models.dtos.UserSeedDto;
import com.example.jsonexdemo.models.entities.User;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUser();
}
