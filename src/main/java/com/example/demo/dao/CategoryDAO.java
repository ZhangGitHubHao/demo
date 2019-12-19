package com.example.demo.dao;

import com.example.demo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
    List<Category> findByName(String name);

    List<Category> findByAge(Integer age);
}
