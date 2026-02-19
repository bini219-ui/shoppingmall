package com.example.shoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  //날짜 자동생성
public class ShoppingmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingmallApplication.class, args);
    }

}
