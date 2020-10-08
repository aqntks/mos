package com.moskhu.web.dto;

import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class OrderMenuListJsonDto {
    private Long id;
    private Collection<JSONObject> menus;
    private Integer total;
    private LocalDateTime modifiedDate;

    public OrderMenuListJsonDto(OrderMenuListResponseDto o) throws ParseException {
        this.id = o.getId();
        this.total = o.getTotal();
        menus = new ArrayList<JSONObject>();
        JSONParser parser = new JSONParser();
        for(String s : o.getMenus())
            menus.add((JSONObject) parser.parse(s));
    }
}
