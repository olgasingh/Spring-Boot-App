package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.Models.Module;
import com.example.demo.Repositories.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ModuleService {

        @Autowired
        private ModuleRepository repo;
         
        public List<Module> listAll() {
            return repo.findAll();
        }
         
        public void save(Module module) {
            repo.save(module);
        }
         
        public Module get(long id) {
            return repo.findById(id).get();
        }
         
        public void delete(long id) {
            repo.deleteById(id);
        }  
}