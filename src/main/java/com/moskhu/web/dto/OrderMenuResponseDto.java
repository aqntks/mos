package com.moskhu.web.dto;

import com.moskhu.domain.posts.OrderMenu;
import lombok.Getter;
import java.util.Collection;

@Getter
public class OrderMenuResponseDto {

    private Long id;
    private Collection<String> menus;
    private Integer total;

    public OrderMenuResponseDto(OrderMenu entity){
        this.id = entity.getId();
        this.menus = entity.getMenus();
        this.total = entity.getTotal();
    }
}
