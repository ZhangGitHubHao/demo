package com.example.demo.dao;

import com.example.demo.pojo.GoodInfoBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodInfoJPA extends JpaRepository<GoodInfoBean,Long> {
}
