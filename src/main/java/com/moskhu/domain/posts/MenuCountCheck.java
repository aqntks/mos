package com.moskhu.domain.posts;

import com.moskhu.web.dto.MenuResponseDto;
import lombok.Getter;

import java.util.Objects;

@Getter
public class MenuCountCheck {
    private Long menuId;
    private String sellerId;
    private String menuName;
    private Integer menuPrice;
    private String menuDescription;
    private String menuImg;
    private Integer menuType;
    private int count;

    public MenuCountCheck(MenuResponseDto m, int count){
        this.menuId = m.getMenuId();
        this.sellerId = m.getSellerId();
        this.menuName = m.getMenuName();
        this.menuPrice = m.getMenuPrice();
        this.menuDescription = m.getMenuDescription();
        this.menuImg = m.getMenuImg();
        this.menuType = m.getMenuType();
        this.count = count;
    }
}
