package com.example.demo.Repositories;

import com.example.demo.Models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


 
public interface ProductRepository extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product> {
 
}