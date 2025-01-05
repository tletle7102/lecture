package com.springboot.lecture.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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

    // @OneToOne(mappedBy = "product")는 Product와 ProductDetail 간의 일대일 양방향 관계를 설정
    // "product"는 ProductDetail 엔티티에서 Product를 참조하는 필드 이름
    // 여기서 ProductDetail 엔티티의 "product" 필드가 관계의 주인으로, 외래 키를 관리
    // 반면, Product 엔티티의 "productDetail" 필드는 연관관계의 주인이 아님
    // @ToString.Exclude는 Product 객체 출력 시 productDetail 필드를 제외
    // mappedBy를 사용하지 않으면 외래키는 @JoinColumn이 달린 엔티티의 테이블에 생성되고,
    // mappedBy를 사용하면 외래키는 주인 엔티티의 테이블에 생성됩니다.
    @OneToOne(mappedBy = "product")
    @ToString.Exclude
    private ProductDetail productDetail;

    // @ManyToOne은 다대일 관계를 설정
    // Product와 Provider 간의 관계에서, 여러 Product가 하나의 Provider에 속함을 나타냄
    // @JoinColumn(name = "provider_id")는 외래 키로 사용할 데이터베이스 컬럼 이름을 "provider_id"로 지정
    // @ToString.Exclude는 Product 객체 출력 시 provider 필드를 제외
    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;

    @ManyToMany
    @ToString.Exclude
    private List<Producer> producers = new ArrayList<>();

    public void addProducer(Producer producer) {

        this.producers.add(producer);
    }

}
