package com.moskhu.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderMenuUpdateRequestDto {
    private Collection<String> menus;
    private Integer total;

    @Builder
    public OrderMenuUpdateRequestDto(Collection<String> menus, Integer total){
        this.menus = menus;
        this.total = total;
    }
}
