package com.springboot.lecture.data.repository.support;

import com.springboot.lecture.data.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findByName(String name);
}
