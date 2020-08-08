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

    @Builder
    public MenuUpdateRequestDto(String sellerId, String menuName, Integer menuPrice, String menuDescription){
        this.sellerId = sellerId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }
}
