package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.Models.Category;
import com.example.demo.Repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository repo;
     
    public List<Category> listAll(int pageNo, int pageSize, String orderBy, String orderDirection) {
        Pageable page = PageRequest.of(pageNo,pageSize,Sort.by(Direction.valueOf(orderDirection), orderBy));
        Category ct = new Category();
        ct.setName("c");
        ExampleMatcher mt = ExampleMatcher.matching().withMatcher("name", m-> m.contains());
        Example<Category> e = Example.of(ct,mt);
        return repo.findAll(e,page).getContent();
    }
    
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
