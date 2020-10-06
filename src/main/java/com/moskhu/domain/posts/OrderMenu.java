package com.moskhu.domain.posts;

import com.moskhu.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
public class OrderMenu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Convert(converter = StringListConverter.class)
    private Collection<String> menus;

    @Column(nullable = false)
    private Integer total;

    @Builder
    public OrderMenu(Collection<String> menus, Integer total){
        this.menus = menus;
        this.total = total;
    }

    public void update(Collection<String> menus, Integer total){
        this.menus = menus;
        this.total = total;
    }
}