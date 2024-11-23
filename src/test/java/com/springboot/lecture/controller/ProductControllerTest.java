package com.springboot.lecture.controller;

import com.google.gson.Gson;
import com.springboot.lecture.data.dto.ProductDto;
import com.springboot.lecture.data.dto.ProductResponseDto;
import com.springboot.lecture.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class) // 배우가 연기할 대상
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // 배우

    @MockBean
    ProductServiceImpl productService; // 대략적인 스토리

    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    //get이니까 getMapping
    void getProductTest() throws Exception{

        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(3371L, "pen", 5000, 200)); // 실제 대본

        String productId = "123";

        mockMvc.perform(get("/product?number=" + productId)) // 감독이 배우에게 큐
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andExpect(jsonPath("$.number").value("3371"))
                .andExpect(jsonPath("$.name").value("pen"))
                .andExpect(jsonPath("$.price").value(5000))
                .andExpect(jsonPath("$.stock").value(200))
                .andDo(print());

        verify(productService).getProduct(123L);

    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    // save니까 insert고 그래서 postMappping
    void createProductTest() throws  Exception{

//        given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
        given(productService.saveProduct(Mockito.any(ProductDto.class)))
                .willReturn(new ProductResponseDto(12315L,"pen", 5000,2000));

        ProductDto productDto = ProductDto.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(
                post("/product")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("pen", 5000,2000));
    }



}