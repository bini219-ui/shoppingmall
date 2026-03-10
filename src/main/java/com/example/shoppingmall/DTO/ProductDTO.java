package com.example.shoppingmall.DTO;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString @Builder
public class ProductDTO {
    private Long id;    //번호
    private String name;    //상품명
    private String description;
    private BigDecimal price;
    private Integer stockQuantity=0;  //재고수량
    private String category;   //카테고리
    private String imageUrl;   //이미지파일
    private LocalDate createdAt;  //등록날짜
    private LocalDate updatedAt;  //수정날짜
    private String status="AVAILABLE";

    private Integer cartCount;  //장바구니에 담긴 횟수
}
