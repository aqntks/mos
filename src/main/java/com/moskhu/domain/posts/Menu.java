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

    @Column(columnDefinition = "TEXT", nullable = false)
    private String menuImg;

    @Column(nullable = false)
    private Integer menuType;

    @Builder
    public Menu(String sellerId, String menuName, Integer menuPrice, String menuDescription, String menuImg, Integer menuType){
        this.sellerId = sellerId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
        this.menuImg = menuImg;
        this.menuType = menuType;
    }

    public void update(String sellerId, String menuName, Integer menuPrice, String menuDescription, String menuImg, Integer menuType){
        this.sellerId = sellerId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
        this.menuImg = menuImg;
        this.menuType = menuType;
    }
}