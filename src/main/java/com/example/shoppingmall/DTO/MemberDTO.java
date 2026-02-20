package com.example.shoppingmall.DTO;

import com.example.shoppingmall.Entity.CartEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SecondaryTable;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String username;  //아이디
    private String password;  //비밀번호
    private String name;    //이름
    private String email;   //이메일
    private String phone;   //전화번호
    private String address; //주소
    private LocalDateTime createdAt;    //가입일자
    private LocalDateTime updatedAt;    //수정일자
    private String status;   //회원상태 - 활성(ACTIVE), 비활성(INACTIVE), 삭제(DELETED)

    private Integer cartItemCount;  //장바구니 항목 수
}
