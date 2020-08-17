package com.moskhu.domain.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class MenuCount {
    private String menuName;
    private Integer menuPrice;
    private int count;
    private int allCount;
    private Long id;
    private String consumerId;
    private Long menuId;

    public MenuCount(String menuName, Integer menuPrice, int count, Basket b){
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.count = count;
        this.allCount = menuPrice * count;
        this.id = b.getId();
        this.consumerId = b.getConsumerId();
        this.menuId = b.getMenuId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuCount == false) return false;
        MenuCount mc = (MenuCount)obj;
        return Objects.equals(this.menuName, mc.menuName) && this.menuPrice == mc.menuPrice && this.count == mc.count && this.allCount == mc.allCount;
    }

}
