package com.moskhu.domain.posts;

import com.moskhu.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Basket extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String consumerId;

    @Column(nullable = false)
    private Long menuId;


    @Builder
    public Basket(String consumerId, Long menuId){
        this.consumerId = consumerId;
        this.menuId = menuId;
    }

    public void update(String consumerId, Long menuId){
        this.consumerId = consumerId;
        this.menuId = menuId;
    }
}