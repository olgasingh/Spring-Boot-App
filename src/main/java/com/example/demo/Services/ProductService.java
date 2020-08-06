package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Models.containers.ListContainer;
import com.example.demo.Models.containers.ListItem;
import com.example.demo.Repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class ProductService {
 
    @Autowired
    private ProductRepository repo;
     
    public List<Product> listAll() {
        return repo.findAll();
    }

    public List<Product> listAll(int pageNo, int pageSize, String orderBy, String orderDirection) {
        Pageable page = PageRequest.of(pageNo,pageSize,Sort.by(Direction.valueOf(orderDirection), orderBy));
        Product ct = new Product();
        Category c = new Category();
        c.setName("f");
        ct.setCategory(c);
        ExampleMatcher mt = ExampleMatcher.matching().withMatcher("category.name", m-> m.contains())
        .withIgnorePaths("price");
        Example<Product> e = Example.of(ct,mt);
        Page<Product> p = repo.findAll(e,page);
        return  p.getContent();
    }

    public List<Product> findByCriteria(String employeeName){
        return repo.findAll(new Specification<Product>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(employeeName!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("category").get("name"), "%" + employeeName + "%")));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public List<Product> findByCriteria(ListContainer listContainer, Long totalPages){
        Pageable pageable = PageRequest.of(listContainer.getPageNo(),listContainer.getPageSize(),Sort.by(Direction.valueOf(listContainer.getOrderDirection()), listContainer.getOrderBy()));
        Page<Product> page = repo.findAll(new Specification<Product>() {
    
            /**
             *
             */
            private static final long serialVersionUID = 1L;            

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                
                List<Predicate> predicates = new ArrayList<>();
                
                for (ListItem listItem : listContainer.getSearchItems()) {
                                    
                    if(!listItem.getFieldValue().isBlank()){
                        String[] fields = listItem.getFieldName().split("\\.");
                        Path<String> path = root.get(fields[0]);
                        for(int i=1; i<fields.length; i++){
                            path = path.get(fields[i]);
                        }                        
                        predicates.add(criteriaBuilder.and(criteriaBuilder.like(path, "%" + listItem.getFieldValue() +"%")));
                    }
                };                
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }}, pageable);
            totalPages = (long) page.getTotalPages();
            //listContainer.set              
        return page.getContent();
    }

    public List<Product> findByCriteria1(String employeeName, Pageable pageable){
        Page<Product> page = repo.findAll(new Specification<Product>() {
    
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(employeeName!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), employeeName)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }}, pageable);              
        return page.getContent();
    }


     
    public void save(Product product) {
        repo.save(product);
    }
     
    public Product get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}