package com.moskhu.web.dto;

import com.moskhu.domain.posts.Menu;
import lombok.Getter;

@Getter
public class MenuResponseDto {
    private Long menuId;
    private String sellerId;
    private String menuName;
    private Integer menuPrice;
    private String menuDescription;
    private String menuImg;
    private Integer menuType;

    public MenuResponseDto(Menu entity){
        this.menuId = entity.getMenuId();
        this.sellerId = entity.getSellerId();
        this.menuName = entity.getMenuName();
        this.menuPrice = entity.getMenuPrice();
        this.menuDescription = entity.getMenuDescription();
        this.menuImg = entity.getMenuImg();
        this.menuType = entity.getMenuType();
    }
}
