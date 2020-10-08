package com.moskhu.domain.posts;

import com.moskhu.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Status extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String seller_id;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean on_off;

    @Builder
    public Status(String seller_id, boolean on_off){
        this.seller_id = seller_id;
        this.on_off = on_off;
    }

    public void update(String seller_id, boolean on_off){
        this.seller_id = seller_id;
        this.on_off = on_off;
    }
}