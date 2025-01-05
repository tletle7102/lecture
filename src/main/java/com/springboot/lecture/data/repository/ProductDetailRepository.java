package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail , Long> {
}
