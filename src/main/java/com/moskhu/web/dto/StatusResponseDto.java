package com.moskhu.web.dto;

import com.moskhu.domain.posts.Status;
import lombok.Getter;

@Getter
public class StatusResponseDto {

    private Long id;
    private String seller_id;
    private boolean on_off;

    public StatusResponseDto(Status entity){
        this.id = entity.getId();
        this.seller_id = entity.getSeller_id();
        this.on_off = entity.isOn_off();
    }
}
