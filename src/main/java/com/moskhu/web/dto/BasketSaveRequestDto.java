package com.moskhu.web.dto;

import com.moskhu.domain.posts.Basket;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BasketSaveRequestDto {
    private Long id;
    private String consumerId;
    private Long menuId;
    @Builder
    public BasketSaveRequestDto(String consumerId, Long menuId){
        this.consumerId = consumerId;
        this.menuId = menuId;
    }

    public Basket toEntity(){
        return Basket.builder()
                .consumerId(consumerId)
                .menuId(menuId)
                .build();
    }
}
