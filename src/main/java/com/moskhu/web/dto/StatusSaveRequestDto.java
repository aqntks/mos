package com.moskhu.web.dto;

import com.moskhu.domain.posts.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StatusSaveRequestDto {
    private String seller_id;
    private boolean on_off;

    @Builder
    public StatusSaveRequestDto(String seller_id, boolean on_off){
        this.seller_id = seller_id;
        this.on_off = on_off;
    }

    public Status toEntity(){
        return Status.builder()
                .seller_id(seller_id)
                .on_off(on_off)
                .build();
    }
}
