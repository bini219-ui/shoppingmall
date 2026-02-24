package com.example.shoppingmall.Repository;

import com.example.shoppingmall.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends
        JpaRepository<CartEntity,Long> {
    //회원ID로 장바구니 목록 조회
    //Member는 CartEntity에 private MemberEntity member에서 온 이름
    //Member테이블에 Id 변수로 조회
    List<CartEntity> findByMemberId(Long memberId);

    //상품ID로 장바구니 목록 조회
    //private ProductEntity product;
    List<CartEntity> findByProductId(Long productId);

    //회원ID와 상품ID로 장바구니 항목 조회
    Optional<CartEntity> findByMemberIdAndProductId(Long memberId, Long productId);

    //회원ID의 장바구니 전체 삭제
    void deleteByMemberId(Long memberId);

    //회원의 장바구니 항목 갯수 조회
    int countByMemberId(Long memberId);

    //회원ID와 상품ID로 장바구니 존재 여부
    boolean existsByMemberIdAndProductId(Long memberId, Long productId);
}
