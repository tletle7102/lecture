package com.springboot.lecture.data.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.lecture.data.entity.Product;
import com.springboot.lecture.data.entity.QProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class QProductRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    QProductRepository qProductRepository;

    @BeforeEach
    void setUp() {
        // 테스트 데이터를 삽입
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(50);
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(3500);
        product2.setStock(30);
        entityManager.persist(product2);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(2000);
        product3.setStock(100);
        entityManager.persist(product3);

        entityManager.flush();
    }

    @Test
    public  void queryDSLTest1() {

        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000, 2500));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if(foundProduct.isPresent()) {
            Product product = foundProduct.get();
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());

        }
    }

    @Test
    public void queryDSLTest2() {

        QProduct qProduct = QProduct.product;

        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("펜")
                        .and(qProduct.price.between(550, 1500))
        );

        for(Product product :  productList) {

            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }

}