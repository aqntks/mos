package com.moskhu.domain.posts;

import com.moskhu.web.dto.MenuResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConsumerOrder {
    private int consumerId;
    private int allCount = 0;
    private List<MenuCountCheck> menu;

    public ConsumerOrder(int consumerId, List<MenuCountCheck> menu){
        this.consumerId = consumerId;
        this.menu = menu;

        for(MenuCountCheck m : menu){
            allCount += m.getMenuPrice() * m.getCount();
        }
    }
}
