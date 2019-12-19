package com.example.demo;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JpaTest {
    @Autowired
    CategoryDAO categoryDAO;
    @Test
    public void test1(){
        List<Category> cs=  categoryDAO.findAll();
        for (Category c : cs) {
            System.out.println("c.getName():"+ c.getName());
        }
    }

    @Test
    public void test2() {
        List<Category> cs=  categoryDAO.findByName("张三");
        for (Category c : cs) {
            System.out.println(c);
        }
        System.out.println();
    }

    @Test
    public void test3() {
        List<Category> cs=  categoryDAO.findByAge(24);
        for (Category c : cs) {
            System.out.println(c);
        }
        System.out.println();
    }
}
