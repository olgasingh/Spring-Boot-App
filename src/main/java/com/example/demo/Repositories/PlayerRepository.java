package com.example.demo.Repositories;

import com.example.demo.Models.Player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
 
}