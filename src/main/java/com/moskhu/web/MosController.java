package com.moskhu.web;

import com.moskhu.domain.posts.*;
import com.moskhu.service.posts.BasketService;
import com.moskhu.service.posts.MenuService;
import com.moskhu.service.posts.OrderListService;
import com.moskhu.service.posts.StatusService;
import com.moskhu.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/payment") //결제 화면
    public String payment(Model model) {
        return "payment";
    }

    @GetMapping("/result") //결과 화면
    public String result(Model model) {
        List<BasketListResponseDto> basket = basketService.findByConsumerId("1");
        ArrayList<MenuResponseDto> list = new ArrayList<>();
        List<MenuCountCheck> result;
        int totalPrice = 0;
        String s = "조리중";

        for(BasketListResponseDto b : basket)
            list.add(menuService.findById(b.getMenuId()));

        result = countCheck(list);

        //총액
        for(MenuCountCheck mc : result)
            totalPrice += mc.getCount() * mc.getMenuPrice();

        //저장
        for(BasketListResponseDto b : basket)
            orderListService.save(new OrderListSaveRequestDto(Integer.parseInt(b.getConsumerId()), b.getMenuId()));


        model.addAttribute("menu", result);
        model.addAttribute("consumerId", 1);
        model.addAttribute("status", s);
        model.addAttribute("totalPrice", totalPrice);
        return "result";
    }

    ////////////////////////////////////////////////////////////////////////////// 판매자

    @GetMapping("/seller_start") //판매자 시작 화면
    public String seller_start(Model model){
            return "seller_start";
    }

    @GetMapping("/sales") //판매 화면
    public String sales(Model model){
        List<OrderListListResponseDto> list = orderListService.findAllConsumerIdAsc();
        List<ArrayList<MenuResponseDto>> temp = new ArrayList<>();
        Set<Integer> orderCount = new TreeSet<>();
        Map<Integer, Integer> index = new TreeMap<>();
        List<ConsumerOrder> consumerOrder = new ArrayList<>();

        //주문 갯수 파악
        for(OrderListListResponseDto o : list)
            orderCount.add(o.getConsumerId());

        //주문자id 인덱스 파악을 위한 map
        int i = 0;
        for(Integer count : orderCount) {
            index.put(count, i);
            i++;
        }

        //주문 갯수 만큼 list 생성 -> temp에 저장
        for(Integer count : orderCount)
            temp.add(new ArrayList<>());


        //고객 별 주문을 temp 내 arrayList에 각각 저장
        for(OrderListListResponseDto o : list){
            for(Integer count : orderCount){
                if(count == o.getConsumerId()){
                    temp.get(index.get(count)).add(menuService.findById(o.getMenuId()));
                    break;
                }
            }
        }
        //ConsumerOrder 리스트에 consumerOrder 정보 저장
        for(Integer count : orderCount) 
            consumerOrder.add(new ConsumerOrder(count, countCheck(temp.get(index.get(count)))));

        //주문 정보
        model.addAttribute("order", consumerOrder);

        //판매 상태 출력
        String str = "";
        if(statusService.existsById(1L)){//status 데이터 존재 여부
            if(statusService.findById(1L).isOn_off()) {
                str += "판매중...";
                model.addAttribute("status", str);
            }
            else {
                str += "준비중...";
                model.addAttribute("status", str);
            }
        }
        else {
            str += "준비중...";
            model.addAttribute("status", str);
        }

        return "sales";
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

    ////////////////////////////////////////////////////////////////////////////////////////

    public List<MenuCountCheck> countCheck(ArrayList<MenuResponseDto> list){
        ArrayList<Long> count = new ArrayList<>();
        Map<Long, Integer> check = new TreeMap<>();

        for(MenuResponseDto m : list) {
            if(check.containsKey(m.getMenuId()))
                check.put(m.getMenuId(), check.get(m.getMenuId()) + 1);
            else
                check.put(m.getMenuId(), 1);
        }

        List<MenuCountCheck> result = new ArrayList<>();

        for(MenuResponseDto m : list) {
            if(!count.contains(m.getMenuId())) {
                result.add(new MenuCountCheck(m, check.get(m.getMenuId())));
                count.add(m.getMenuId());
            }
        }

        return result;
    }


}


