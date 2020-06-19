package com.example.demo.Repositories;

import com.example.demo.Models.Module;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    
}