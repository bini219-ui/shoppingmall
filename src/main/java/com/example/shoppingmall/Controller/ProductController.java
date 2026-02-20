package com.example.shoppingmall.Controller;

import com.example.shoppingmall.DTO.ProductDTO;
import com.example.shoppingmall.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

//400(Mapping 확인), 405(Return 확인) 오류
@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    //상품 목록
    @GetMapping
    public String listProducts(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    //상품 등록폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "product/create";
    }

    //상품 등록 처리
    @PostMapping("/new")
    public String createProduct(ProductDTO productDTO) {
        try {
            productService.createProduct(productDTO);
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            return "redirect:/product/new?error"+ e.getMessage();
        }
    }

    //상품 상세 페이지
    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        ProductDTO product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/view";
    }

    //상품 수정폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        ProductDTO product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/edit";
    }

    //상품 수정 처리
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, ProductDTO productDTO) {
        try {
            productService.updatedProduct(id, productDTO);
            return "redirect:/products" + id;
        } catch (IllegalArgumentException e) {
            return "redirect:/products/edit/" + id + "?error=" + e.getMessage();
        }
    }
    
    //상품 삭제
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
    
    //다양한 검색을 통한 조회
    //상품 검생
    @GetMapping("/search")  //keyword 오류 발생 시 @requestParam을 붙인다.
    public String searchProducts(String keyword, Model model) {
        List<ProductDTO> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "product/list";
    }
    
    //가격 범위로 상품 조회
    @GetMapping("/price-range")
    public String getProductsRange(BigDecimal minPrice, BigDecimal maxPrice, Model model){
        List<ProductDTO> products= productService.getProductsByPriceRange(minPrice, maxPrice);
        model.addAttribute("products", products);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "product/list";
    }
    
    //재고 있는 상품만 조회
    @GetMapping("available")
    public String getAvailableProducts(Model model) {
        List<ProductDTO> products = productService.getavailableProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    //재고 관리폼
    @GetMapping("/stock/{id}")
    public String stockManagementForm(@PathVariable Long id, Model model) {
        ProductDTO product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/stock";
    }

    //재고 증가 처리
    @PostMapping("/stock/increase/{id}")
    public String increaseStock(@PathVariable Long id, Integer quantity) {
        productService.increaseStock(id, quantity);
        return "redirect:/products/" +id;
    }

    //재고 감소 처기
    @PostMapping("/stock/decrease/{id}")
    public String decreaseStock(@PathVariable Long id, Integer quantity) {
        try {
            productService.decreaseStock(id, quantity);
            return "redirect:/products/" +id;
        } catch (IllegalStateException e) {
            return "redirect:/products/stock/" + id + "?error=" + e.getMessage();
        }
    }
}