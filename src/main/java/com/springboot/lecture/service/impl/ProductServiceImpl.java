package com.springboot.lecture.service.impl;

import com.springboot.lecture.data.dao.ProductDAO;
import com.springboot.lecture.data.dto.ProductDto;
import com.springboot.lecture.data.dto.ProductResponseDto;
import com.springboot.lecture.data.entity.Product;
import com.springboot.lecture.data.repository.ProductRepository;
import com.springboot.lecture.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
// 커피머신
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;


    //  생성자로 필드(ProductDAO) 초기화 == 의존성주입
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 자동완성으로 오버라이딩 메서드 설정하기
    @Override
    public ProductResponseDto getProduct(Long number) {

        LOGGER.info("[getProduct] input number : {}", number);
        Product product =productRepository.findById(number).get();

        LOGGER.info("[getProduct] product number : {} , name : {}", product.getNumber(),
                product.getName());

        // getProduct메서드의 반환타입이 ProductResponseDto이므로,
        // ProductResponseDto 타입의 인스턴스를 생성하여,
        // productResponseDto라는 변수에 할당함
        ProductResponseDto productResponseDto = new ProductResponseDto();

        // 아래 4줄의 코드들을 통해서,
        // "productResponseDto라는 변수에 할당되어 있는
        // ProductResponseDto 타입 객체의 각 필드"에 setter를 통해,
        // "product라는 변수에 담긴 대응되는 필드들을 getter로 꺼낸 값"을 parameter로 할당함
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        // productResponseDto라는 변수에 할당되어 있으면서 필드가 모두 채워진,
        // ProductResponseDto 타입 객체를 반환함
        return productResponseDto;

    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

        LOGGER.info("[saveProduct] productDTO : {}", productDto.toString());

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());


        Product savedProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] savedProduct : {}", savedProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {

        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changeProduct =  productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changeProduct.getNumber());
        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setPrice(changeProduct.getPrice());
        productResponseDto.setStock(changeProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        productRepository.deleteById(number);

    }
}
