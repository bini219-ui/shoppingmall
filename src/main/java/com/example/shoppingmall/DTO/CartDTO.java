package com.example.shoppingmall.DTO;

import com.example.shoppingmall.Entity.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    //cart의 기본데이터
    private Long id; //장바구니 id번호
    private Long memberId; //소유 회원의 id번호
    private Long productId; //구매 상품의 id번호
    private Integer quantity; //구매수량
    private LocalDateTime createdAt; //등록일자

    //조회 시 사용
    private String memberName; //회원이름
    private String productName; //상품명
    private BigDecimal productPrice; //상품 가격
    private BigDecimal totalPrice; //총가격
    private String productImageUrl; //상품이미지
}
/*
* Entity는 부모의 테이블을 지정(데이터베이스에 id 저장)
* DTO에서는 부모 테이블의 참조된 id번호를 지정
* */
