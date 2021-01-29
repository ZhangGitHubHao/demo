package com.example.demo.dao;

import com.example.demo.pojo.GoodTypeBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodTypeJPA extends JpaRepository<GoodTypeBean,Long> {
}
