package com.moskhu.web.dto;

import com.moskhu.domain.posts.OrderList;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class OrderListListResponseDto {
    private Long id;
    private Integer consumerId;
    private Long MenuId;
    private LocalDateTime modifiedDate;

    public OrderListListResponseDto(OrderList entity) {
        this.id = entity.getId();
        this.consumerId = entity.getConsumerId();
        this.MenuId = entity.getMenuId();
        this.modifiedDate = entity.getModifiedDate();
    }
}
