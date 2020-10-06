package com.moskhu.web.dto;

import com.moskhu.domain.posts.OrderMenu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderMenuSaveRequestDto {
    private Collection<String> menus;
    private Integer total;
    @Builder
    public OrderMenuSaveRequestDto(Collection<String> menus, Integer total){
        this.menus = menus;
        this.total = total;
    }

    public OrderMenu toEntity(){
        return OrderMenu.builder()
                .menus(menus)
                .total(total)
                .build();
    }
}
