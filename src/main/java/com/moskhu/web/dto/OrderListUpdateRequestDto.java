package com.moskhu.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderListUpdateRequestDto {
    private Integer consumerId;
    private Long menuId;

    @Builder
    public OrderListUpdateRequestDto(Integer consumerId, Long menuId){
        this.consumerId = consumerId;
        this.menuId = menuId;
    }
}
