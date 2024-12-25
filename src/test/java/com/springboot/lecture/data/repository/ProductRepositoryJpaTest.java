package com.springboot.lecture.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.lecture.data.entity.Product;
import com.springboot.lecture.data.entity.QProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

@Transactional // 트랜잭션 활성화
@SpringBootTest
class ProductRepositoryJpaTest {


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

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
    void sortingAndPagingTest(){

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        System.out.println("=============");
        System.out.println(productRepository.findByName("펜", getSort()));
        System.out.println("=============");

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0,2));
        System.out.println("=============");
        System.out.println(productPage);
        System.out.println("=============");

        System.out.println("=============");
        System.out.println(productPage.getContent());
        System.out.println("=============");

        List<Product> nameFromFirstParam = productRepository.findByName("펜");
        List<Product> nameFromNameParam = productRepository.findByNameParam("펜");
        System.out.println("=============");
        System.out.println(nameFromFirstParam);
        System.out.println(nameFromNameParam);
        System.out.println("=============");

        List<Object[]> selectSpecificcolumn = productRepository.findByNameParam2("펜");
        System.out.println(selectSpecificcolumn);

    }

    @Test
    void queryDslTest(){
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        //위에는 반환타입만 바꿔주고
        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();
        //아래는 페치와 관련된 사용하고 싶은 메서드만 적는다(fetchone(), fetchFirst()등)

        for(Product product : productList) {
            System.out.println("---------");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println();
            System.out.println("---------");
        }

    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product product : productList) {
            System.out.println("---------");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println();
            System.out.println("---------");
        }
    }

    @Test
    void queryDslTest3() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println("---------");
            System.out.println("Product Name : " + product);
            System.out.println("---------");
        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple product : tupleList) {
            System.out.println("----------------");
            System.out.println("Product Name : " + product.get(qProduct.name));
            System.out.println("Product Price : " + product.get(qProduct.price));
            System.out.println("----------------");
        }
    }

    @Test
    void queryDslTest4() {

        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println("----------------");
            System.out.println("Product Name : " + product);
            System.out.println("----------------");
        }

    }

    private Sort getSort(){
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }
}
