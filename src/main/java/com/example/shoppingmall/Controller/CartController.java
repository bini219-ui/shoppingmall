package com.example.shoppingmall.Controller;

import com.example.shoppingmall.DTO.CartDTO;
import com.example.shoppingmall.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    //장바구니 목록 조회
    @GetMapping("/{memberId}")
    public String viewCart(@PathVariable Long memberId, Model model) {
        List<CartDTO> cartItems = cartService.getCartByMember(memberId);
        BigDecimal totalPrice = cartService.getTotalPrice(memberId);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("memberId", memberId);
        return "cart/view";
    }

    //장바구니에 상품 추가
    @PostMapping("/add")
    public String addToCart(@RequestParam Long memberId, @RequestParam Long productId,
                            @RequestParam(defaultValue = "1") Integer quantity) {
        try {
            CartDTO dto = CartDTO.builder().memberId(memberId)
                    .productId(productId).quantity(quantity).build();
            cartService.addToCart(dto);
            return "redirect:/cart/"+memberId;
        } catch (IllegalStateException e) {
            return "redirect:/products/"+productId+"?error="+e.getMessage();
        }
    }

    //장바구니 수량 변경
    @PostMapping("/update/{id}")
    public String updateQuantity(@PathVariable Long id, @RequestParam Integer quntity,
                                 @RequestParam Long memberId) {
        try {
            cartService.updateQuantity(id, quntity);
            return "redirect:/cart/"+memberId;
        } catch (IllegalStateException | IllegalArgumentException e) {
            return "redirect:/cart/"+memberId+"?error="+e.getMessage();
        }
    }

    //장바구니의 항목 삭제
    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable Long id, @RequestParam Long memberId) {
        cartService.deleteCart(id);
        return "redirect:/cart/"+memberId;
    }

    //장바구니 전체 비우기
    @GetMapping("/clear/{memberId}")
    public String clearCart(@PathVariable Long memberId) {
        cartService.clearCart(memberId);
        return "redirect:/cart/"+memberId;
    }

    //장바구니 수량 증가
    @PostMapping("/increase/{id}")
    public String increaseQuantity(@PathVariable Long id, @RequestParam Long memberId) {
        try {
            CartDTO cart = cartService.getCart(id);
            cartService.updateQuantity(id, cart.getQuantity()+1);
            return "redirect:/cart/"+memberId;
        } catch (IllegalStateException e) {
            return "redirect:/cart/"+memberId+"?error="+e.getMessage();
        }
    }

    //장바구니 수량 감소
    @PostMapping("/decrease/{id}")
    public String decreaseQuantity(@PathVariable Long id, @RequestParam Long memberId) {
        try {
            CartDTO cart = cartService.getCart(id);
            if (cart.getQuantity()>1) {
                cartService.updateQuantity(id, cart.getQuantity() - 1);
            } else {
                cartService.deleteCart(id);
            }
            return "redirect:/cart/"+memberId;
        } catch (IllegalStateException e) {
            return "redirect:/cart/"+memberId+"?error="+e.getMessage();
        }
    }

    //장바구니 항목 개수 조회(ajax)


    //장바구니 총 금액 조회(ajax)
}
