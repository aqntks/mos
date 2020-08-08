package com.moskhu.web.dto;

import com.moskhu.domain.posts.OrderList;
import lombok.Getter;

@Getter
public class OrderListResponseDto {

    private Long id;
    private String consumerId;
    private Long MenuId;

    public OrderListResponseDto(OrderList entity){
        this.id = entity.getId();
        this.consumerId = entity.getConsumerId();
        this.MenuId = entity.getMenuId();
    }
}
