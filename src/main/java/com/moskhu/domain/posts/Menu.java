package com.moskhu.domain.posts;

import com.moskhu.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Menu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String sellerId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String menuName;

    @Column(nullable = false)
    private Integer menuPrice;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String menuDescription;

    @Builder
    public Menu(String sellerId, String menuName, Integer menuPrice, String menuDescription){
        this.sellerId = sellerId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }

    public void update(String sellerId, String menuName, Integer menuPrice, String menuDescription){
        this.sellerId = sellerId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }
}