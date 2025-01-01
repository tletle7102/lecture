package com.springboot.lecture.data.repository.support;

import com.springboot.lecture.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository("productRepositorySupport")
public interface ProductRepositorySupport extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
