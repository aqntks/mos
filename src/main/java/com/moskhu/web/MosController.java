package com.moskhu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MosController {

    @GetMapping("/") //홈 화면
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/cart") //장바구니 화면
    public String cart(Model model) {
        return "cart";
    }
}
