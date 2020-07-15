package com.example.demo.Repositories;

import java.util.List;

public interface StatRepositoryCustom {

    List<Object[]> getTopFive(String stField);
    List<Object[]> getButtomFive(String stField);
    
}