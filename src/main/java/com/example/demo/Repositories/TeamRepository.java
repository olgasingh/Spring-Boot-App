package com.example.demo.Repositories;

import com.example.demo.Models.Team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
 
}