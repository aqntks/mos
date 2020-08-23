package com.moskhu.domain.posts;

import com.moskhu.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class OrderList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer consumerId;

    @Column(nullable = false)
    private Long menuId;


    @Builder
    public OrderList(Integer consumerId, Long menuId){
        this.consumerId = consumerId;
        this.menuId = menuId;
    }

    public void update(Integer consumerId, Long menuId){
        this.consumerId = consumerId;
        this.menuId = menuId;
    }
}