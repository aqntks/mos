package com.moskhu.web;

import com.moskhu.domain.posts.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

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
        List<Basket> basket = basketRepository.findAllDesc();
        List<MenuCount> list = new ArrayList<>();
        //List 저장 인덱스 확인용
        Map<String, Integer> check = new TreeMap<>();
        int index = 0;
        int totalPrice = 0;

        for(Basket b : basket) {
            Optional<Menu> menu = menuRepository.findById(b.getMenuId());
            //이미 존재하는 경우
            if(check.containsKey(menu.get().getMenuName())) {
                list.set(check.get(menu.get().getMenuName()), new MenuCount(menu.get().getMenuName(), menu.get().getMenuPrice(), list.get(check.get(menu.get().getMenuName())).getCount()+1, b));

            }
            //아닌 경우
            else{
                check.put(menu.get().getMenuName(), index++);
                list.add(new MenuCount(menu.get().getMenuName(), menu.get().getMenuPrice(), 1, b));
            }
        }

        //총액
        for(MenuCount mc : list)
            totalPrice += mc.getAllCount();

        model.addAttribute("basket", list);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }
}
