package com.springboot.lecture.data.repository.support;

import com.springboot.lecture.data.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional // 트랜잭션 활성화
@SpringBootTest
class ProductRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    ProductRepositorySupport productRepositorySupport;

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
        product2.setPrice(1500);
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
    void findByNameTest(){
        List<Product> productList = productRepositorySupport.findByName("펜");

        for(Product product :  productList) {
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }
}