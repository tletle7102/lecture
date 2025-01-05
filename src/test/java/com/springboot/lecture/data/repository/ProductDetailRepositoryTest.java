package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.Product;
import com.springboot.lecture.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional // 트랜잭션 범위 내에서 데이터베이스 작업 처리
    public void saveAndTest1() {

        // Product 생성 및 저장
        Product product = new Product();
        product.setName("스프링부트 JPA");
        product.setPrice(5000);
        product.setStock(500);
        productRepository.save(product); // product가 영속성 컨텍스트에 저장됨

        // ProductDetail 생성 및 저장
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);  // 이미 저장된 product 객체를 연결
        productDetail.setDescription("스프링부트와 JPA를 함께 볼 수 있는 책");
        productDetailRepository.save(productDetail); // productDetail도 영속성 컨텍스트에 저장됨

        // 생성한 데이터 조회 및 출력
        Optional<ProductDetail> savedProductDetailOpt = productDetailRepository.findById(productDetail.getId());
        if (savedProductDetailOpt.isPresent()) {
            ProductDetail savedProductDetail = savedProductDetailOpt.get();
            System.out.println("savedProduct : " + savedProductDetail.getProduct()); // 연관된 product 출력
            System.out.println("savedProductDetail : " + savedProductDetail);  // 저장된 productDetail 출력
        }
    }
}
