package com.moskhu.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StatusUpdateRequestDto {
    private String seller_id;
    private boolean on_off;

    @Builder
    public StatusUpdateRequestDto(String seller_id, boolean on_off){
        this.seller_id = seller_id;
        this.on_off = on_off;
    }
}
