package com.moskhu.web;

import com.moskhu.domain.posts.BasketRepository;
import com.moskhu.domain.posts.Menu;
import com.moskhu.domain.posts.MenuRepository;
import com.moskhu.domain.posts.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MosController {

    private final BasketRepository basketRepository;
    private final MenuRepository menuRepository;
    private final OrderListRepository orderListRepository;

    @GetMapping("/") //홈 화면
    public String home(Model model) {
        List<Menu> menu = menuRepository.findAllDesc();

        List<Menu> type1 = new ArrayList<>();
        List<Menu> type2 = new ArrayList<>();
        List<Menu> type3 = new ArrayList<>();
        List<Menu> type4 = new ArrayList<>();
        List<Menu> type5 = new ArrayList<>();

        for(Menu m : menu) {
            switch(m.getMenuType())
            {
                case 1:
                    type1.add(m);
                    break;
                case 2:
                    type2.add(m);
                    break;
                case 3:
                    type3.add(m);
                    break;
                case 4:
                    type4.add(m);
                    break;
                case 5:
                    type5.add(m);
                    break;
                default:
            }
        }

        model.addAttribute("menu", menu);
        model.addAttribute("type1", type1);
        model.addAttribute("type2", type2);
        model.addAttribute("type3", type3);
        model.addAttribute("type4", type4);
        model.addAttribute("type5", type5);
        return "home";
    }

    @GetMapping("/cart") //장바구니 화면
    public String cart(Model model) {
        model.addAttribute("basket", basketRepository.findAllDesc());
        return "cart";
    }
}
