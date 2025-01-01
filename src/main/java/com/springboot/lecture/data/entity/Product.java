package com.springboot.lecture.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "name", callSuper = true)
@Table(name="product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long number;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private Integer price;

    @Column(nullable=false)
    private Integer stock;
}
