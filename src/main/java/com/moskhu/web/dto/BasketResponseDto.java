package com.moskhu.web.dto;

import com.moskhu.domain.posts.Basket;
import lombok.Getter;

@Getter
public class BasketResponseDto {

    private Long id;
    private String consumerId;
    private Long MenuId;

    public BasketResponseDto(Basket entity){
        this.id = entity.getId();
        this.consumerId = entity.getConsumerId();
        this.MenuId = entity.getMenuId();
    }
}
