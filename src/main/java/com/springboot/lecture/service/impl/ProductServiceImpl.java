package com.springboot.lecture.service.impl;

import com.springboot.lecture.data.dao.ProductDAO;
import com.springboot.lecture.data.dto.ProductDto;
import com.springboot.lecture.data.dto.ProductResponseDto;
import com.springboot.lecture.data.entity.Product;
import com.springboot.lecture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// 커피머신
public class ProductServiceImpl implements ProductService {

    //  ProductServiceImpl을 사용하기 위해 밑에 DAO부분을 먼저 선언해야함(먼저 존재해야만하니까, 커피캡슐)
    private final ProductDAO productDAO;


    //  생성자로 필드(ProductDAO) 초기화 == 의존성주입
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // 자동완성으로 오버라이딩 메서드 설정하기
    @Override
    public ProductResponseDto getProduct(Long number) {

        // 선언하고 생성자로 초기화한(DI한) productDAO를 이용해서 number(parameter)로
        // Product타입의 데이터를 조회해서 product라는 변수에 할당함
        Product product = productDAO.selectProduct(number);

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
        return null;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        return null;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

    }
}
