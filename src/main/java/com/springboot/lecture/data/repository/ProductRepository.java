package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// DAO 또는 서비스에서 DI(의존성주입)해서 사용하기 위해서 리포지토리를 가장 먼저 문서화해야함(일빠)
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name, Sort sort);
    Page<Product> findByName(String name, Pageable pageable);

}
