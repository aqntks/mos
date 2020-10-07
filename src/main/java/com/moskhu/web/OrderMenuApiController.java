package com.moskhu.web;

import com.moskhu.service.posts.OrderMenuService;
import com.moskhu.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderMenuApiController {

    private final OrderMenuService orderMenuService;

    @PostMapping("/api/v1/orderMenu")
    public Long save(@RequestBody OrderMenuSaveRequestDto requestDto) {
        return orderMenuService.save(requestDto);
    }

    @PutMapping("/api/v1/orderMenu/{id}")
    public Long update(@PathVariable Long id, @RequestBody OrderMenuUpdateRequestDto requestDto) {
        return orderMenuService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/orderMenu/{id}")
    public Long delete(@PathVariable Long id) {
        orderMenuService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/orderMenu/{id}")
    public OrderMenuResponseDto findById(@PathVariable Long id) {
        return orderMenuService.findById(id);
    }

    @GetMapping("/api/v1/orderMenu/list")
    public List<OrderMenuListResponseDto> findAll() {
        return orderMenuService.findAllDesc();
    }
}
