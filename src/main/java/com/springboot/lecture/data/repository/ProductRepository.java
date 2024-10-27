package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {



}
