package com.example.jsonexdemo.services;

import com.example.jsonexdemo.models.dtos.UserSeedDto;
import com.example.jsonexdemo.models.entities.User;
import com.example.jsonexdemo.models.utils.ValidationUtill;
import com.example.jsonexdemo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private final ValidationUtill validationUtill;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtill validationUtill) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtill = validationUtill;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        if (this.userRepository.count() != 0) {
            return;
        }

        Arrays.stream(userSeedDtos)
                .forEach(userSeedDto -> {
                    if (this.validationUtill.isValid(userSeedDto)) {
                        User user = this.modelMapper
                                .map(userSeedDto, User.class);
                        this.userRepository
                                .saveAndFlush(user);
                    } else {
                        this.validationUtill
                                .violations(userSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });

    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        long randomId = random
                .nextInt((int) this.userRepository.count()) + 1;

        return this.userRepository.getOne(randomId);
    }
}
