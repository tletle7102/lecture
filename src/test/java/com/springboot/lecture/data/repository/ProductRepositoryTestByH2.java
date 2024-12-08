package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JPA 관련된 설정만 테스트, 기본적으로  @Transactional 어노테이션을 포함해서 테스트코드가 종료되면
 * 디비의 롤백이 이뤄진다.기본값으로 임베디드 디비를 사용함.
 */
@DataJpaTest
class ProductRepositoryTestByH2 {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest(){

        //given 가정 (테스트에서 사용할 Product 엔티티 만들고)
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        //when 실제 실행 흉내 ( 생성된 엔티티를 기반으로 save() 메서드를 호출해 테스트 진행)
        Product savedProduct = productRepository.save(product);

        //then 검증 (생성한 엔티티 객체의 값이 일치하는지 검증)
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }

    @Test
    void selectTest(){

        //given 가정 (테스트에서 사용할 Product 엔티티 만들고)
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.saveAndFlush(product);

        //when
        Product foundProduct = productRepository
                .findById(savedProduct.getNumber())
                .get();

        //then
        assertEquals(product.getName(), foundProduct.getName());
        assertEquals(product.getPrice(), foundProduct.getPrice());
        assertEquals(product.getStock(), foundProduct.getStock());

    }
}