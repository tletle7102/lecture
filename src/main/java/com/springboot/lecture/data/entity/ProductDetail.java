package com.springboot.lecture.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String description;

    // @OneToOne은 일대일 단방향 관계를 설정하며, 두 객체 간의 관계를 정의함
    // @JoinColumn(name = "product_number")는 외래키 컬럼의 이름을 지정하며,
    // 이 컬럼은 실제로 데이터베이스에 컬럼으로 생성되어 Product 테이블의 기본키를 참조함
    // @JoinColumn이 외래키 컬럼을 생성하고 관계를 설정하는 역할을 함
    // 필드에는 외래키 컬럼이 명시되지 않지만, JPA가 자동으로 테이블에 외래키 컬럼을 생성하여 관계를 나타냄
    @OneToOne
    @JoinColumn(name = "product_number")
    @ToString.Exclude  // 순환 참조 방지
    @EqualsAndHashCode.Exclude  // 순환 참조 방지
    private Product product;


}
