package com.moskhu.web.dto;

import com.moskhu.domain.posts.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuSaveRequestDto {
    private String sellerId;
    private String menuName;
    private Integer menuPrice;
    private String menuDescription;
    private String menuImg;
    private Integer menuType;
    @Builder
    public MenuSaveRequestDto(String sellerId, String menuName, Integer menuPrice, String menuDescription, String menuImg, Integer menuType){
        this.sellerId = sellerId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
        this.menuImg = menuImg;
        this.menuType = menuType;
    }

    public Menu toEntity(){
        return Menu.builder()
                .sellerId(sellerId)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuDescription(menuDescription)
                .menuImg(menuImg)
                .menuType(menuType)
                .build();
    }
}
