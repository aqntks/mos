package com.moskhu.web.dto;

import com.moskhu.domain.posts.Basket;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BasketListResponseDto {
    private Long id;
    private String consumerId;
    private Long MenuId;
    private LocalDateTime modifiedDate;

    public BasketListResponseDto(Basket entity) {
        this.id = entity.getId();
        this.consumerId = entity.getConsumerId();
        this.MenuId = entity.getMenuId();
        this.modifiedDate = entity.getModifiedDate();
    }
}
