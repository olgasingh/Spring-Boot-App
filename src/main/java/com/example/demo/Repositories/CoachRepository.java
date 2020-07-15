package com.example.demo.Repositories;

import com.example.demo.Models.Coach;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
 
}