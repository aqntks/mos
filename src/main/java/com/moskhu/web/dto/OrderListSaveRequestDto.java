package com.moskhu.web.dto;

import com.moskhu.domain.posts.OrderList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderListSaveRequestDto {
    private String consumerId;
    private Long menuId;
    @Builder
    public OrderListSaveRequestDto(String consumerId, Long menuId){
        this.consumerId = consumerId;
        this.menuId = menuId;
    }

    public OrderList toEntity(){
        return OrderList.builder()
                .consumerId(consumerId)
                .menuId(menuId)
                .build();
    }
}
