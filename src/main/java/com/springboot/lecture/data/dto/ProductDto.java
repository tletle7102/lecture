package com.springboot.lecture.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private String name;
    private int price;
    private int stock;

    public ProductDto() {
    }

    public ProductDto(String name, int price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
