package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.Models.Category;
import com.example.demo.Repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository repo;
     
    public List<Category> listAll() {
        return repo.findAll();
    }
     
    public void save(Category category) {
        repo.save(category);
    }
     
    public Category get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
