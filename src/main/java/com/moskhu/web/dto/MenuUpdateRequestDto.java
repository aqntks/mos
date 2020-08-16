package com.moskhu.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuUpdateRequestDto {
    private String sellerId;
    private String menuName;
    private Integer menuPrice;
    private String menuDescription;
    private String menuImg;
    private Integer menuType;

    @Builder
    public MenuUpdateRequestDto(String sellerId, String menuName, Integer menuPrice, String menuDescription, String menuImg, Integer menuType){
        this.sellerId = sellerId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
        this.menuImg = menuImg;
        this.menuType = menuType;
    }
}
