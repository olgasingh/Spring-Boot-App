package com.example.demo.Repositories;

import com.example.demo.Models.Game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
 
}