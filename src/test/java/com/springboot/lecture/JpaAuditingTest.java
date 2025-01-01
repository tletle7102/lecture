package com.springboot.lecture;

import com.springboot.lecture.data.entity.Product;
import com.springboot.lecture.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaAuditingTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void auditingTest() {

        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(100);

        Product savedProduct = productRepository.save(product);

        System.out.println("productName : " + savedProduct.getName());
        System.out.println("createdAt : " + savedProduct.getCreatedAt());
    }
}
