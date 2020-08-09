package com.moskhu.web;

import com.moskhu.web.dto.*;
import com.moskhu.service.posts.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MenuApiController {

    private final MenuService menuService;

    @PostMapping("/api/v1/menu")
    public Long save(@RequestBody MenuSaveRequestDto requestDto) {
        return menuService.save(requestDto);
    }

    @PutMapping("/api/v1/menu/{menuId}")
    public Long update(@PathVariable Long menuId, @RequestBody MenuUpdateRequestDto requestDto) {
        return menuService.update(menuId, requestDto);
    }

    @DeleteMapping("/api/v1/menu/{menuId}")
    public Long delete(@PathVariable Long menuId) {
        menuService.delete(menuId);
        return menuId;
    }

    @GetMapping("/api/v1/menu/{menuId}")
    public MenuResponseDto findById(@PathVariable Long menuId) {
        return menuService.findById(menuId);
    }

    @GetMapping("/api/v1/menu/list")
    public List<MenuListResponseDto> findAll() {
        return menuService.findAllDesc();
    }
}
