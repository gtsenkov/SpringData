package com.example.springdataautomappingobjects.repositories;

import com.example.springdataautomappingobjects.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
