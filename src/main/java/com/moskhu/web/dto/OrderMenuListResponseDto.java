package com.moskhu.web.dto;

import com.moskhu.domain.posts.OrderMenu;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
public class OrderMenuListResponseDto {
    private Long id;
    private Collection<String> menus;
    private Integer total;
    private LocalDateTime modifiedDate;

    public OrderMenuListResponseDto(OrderMenu entity) {
        this.id = entity.getId();
        this.menus = entity.getMenus();
        this.total = entity.getTotal();
        this.modifiedDate = entity.getModifiedDate();
    }
}
