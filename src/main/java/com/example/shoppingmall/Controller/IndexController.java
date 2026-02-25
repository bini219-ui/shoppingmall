package com.example.shoppingmall.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private static final List<String> CATEGORIES =
            List.of("전체", "교육", "음악", "게임", "여행", "요리", "스포츠", "엔터테인먼트", "뉴스", "기타");
    @GetMapping
    public String index() {
        return "index";
    }
}
