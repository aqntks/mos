package com.moskhu.web;

import com.moskhu.domain.posts.*;
import com.moskhu.service.posts.BasketService;
import com.moskhu.service.posts.MenuService;
import com.moskhu.service.posts.OrderListService;
import com.moskhu.service.posts.StatusService;
import com.moskhu.web.dto.MenuListResponseDto;
import com.moskhu.web.dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@RequiredArgsConstructor
@Controller
public class MosController {

    private final BasketService basketService;
    private final MenuService menuService;
    private final OrderListService orderListService;
    private final StatusService statusService;

    private final MenuRepository menuRepository;
    private final BasketRepository basketRepository;



    @GetMapping("/") //시작 화면
    public String start(Model model){
        if(statusService.existsById(1L)){//status 데이터 존재 여부
            if(statusService.findById(1L).isOn_off())
                return "start";
            else
                return "preparing";
        }
        else
            return "preparing";
    }

    @GetMapping("/order") //주문 화면
    public String order(Model model) {
        List<MenuListResponseDto> menu = menuService.findAllDesc();

        List<MenuListResponseDto> type1 = new ArrayList<>();
        List<MenuListResponseDto> type2 = new ArrayList<>();
        List<MenuListResponseDto> type3 = new ArrayList<>();
        List<MenuListResponseDto> type4 = new ArrayList<>();
        List<MenuListResponseDto> type5 = new ArrayList<>();

        for(MenuListResponseDto m : menu) {
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
        return "order";
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

    @GetMapping("/seller_start") //시작 화면
    public String seller_start(Model model){
            return "seller_start";
    }

    @GetMapping("/menu_management") //메뉴 관리 화면
    public String menu_management(Model model) {
        List<MenuListResponseDto> menu = menuService.findAllDesc();

        List<MenuListResponseDto> type1 = new ArrayList<>();
        List<MenuListResponseDto> type2 = new ArrayList<>();
        List<MenuListResponseDto> type3 = new ArrayList<>();
        List<MenuListResponseDto> type4 = new ArrayList<>();
        List<MenuListResponseDto> type5 = new ArrayList<>();

        for(MenuListResponseDto m : menu) {
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
        return "menu_management";
    }

    @GetMapping("/add_menu") //메뉴 추가 화면
    public String add_menu(Model model){
            return "add_menu";
    }

    @GetMapping("/edit_menu") //메뉴 수정 화면
    public String edit_menu1(Model model){
        return "edit_menu";
    }

    @GetMapping("/edit_menu/{menuId}") //메뉴 수정 화면
    public String edit_menu(@PathVariable Long menuId, Model model){
        MenuResponseDto menu = menuService.findById(menuId);
        model.addAttribute("menu", menu);
        return "edit_menu";
    }
}
