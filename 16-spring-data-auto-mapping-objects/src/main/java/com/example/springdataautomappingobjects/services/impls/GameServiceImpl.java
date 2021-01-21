package com.example.springdataautomappingobjects.services.impls;

import com.example.springdataautomappingobjects.domain.dtos.GameAddDto;
import com.example.springdataautomappingobjects.domain.entities.Game;
import com.example.springdataautomappingobjects.repositories.GameRepository;
import com.example.springdataautomappingobjects.services.GameService;
import com.example.springdataautomappingobjects.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        if (!this.userService.isLoggedUserAdmin()) {
            System.out.println("Logged user is not admin");
            return;
        }
        Game game = this.modelMapper
                .map(gameAddDto, Game.class);

        this.gameRepository.saveAndFlush(game);
    }
}
