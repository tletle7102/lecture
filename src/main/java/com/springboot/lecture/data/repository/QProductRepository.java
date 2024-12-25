package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository extends JpaRepository<Product, Long> ,
    QuerydslPredicateExecutor<Product> {
}
