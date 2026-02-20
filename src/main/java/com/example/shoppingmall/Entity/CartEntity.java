package com.example.shoppingmall.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="carts",
//member_id와 product_id는 유일한 키로만 존재(생략가능)
uniqueConstraints = {@UniqueConstraint(
        columnNames = {"member_id", "product_id"})})
@Getter @Setter
//toString 사용 시 2개의 필드는 제외
@ToString(exclude = {"member", "product"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Builder.Default
    private Integer quantity=1;  //수량
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createdAt;

    //이용할 부모테이블 지정
    //회원테이블과 연관(하나의 회원은 여러 장바구니에 존재 가능)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private MemberEntity member;

    //상품테이블과 연관(하나의 상품은 여러 장바구니에 존재 가능)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private ProductEntity product;
}
