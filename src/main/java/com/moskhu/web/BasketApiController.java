package com.moskhu.web;

import com.moskhu.web.dto.*;
import com.moskhu.service.posts.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BasketApiController {

    private final BasketService basketService;

    @PostMapping("/api/v1/basket")
    public Long save(@RequestBody BasketSaveRequestDto requestDto) {
        return basketService.save(requestDto);
    }

    @PutMapping("/api/v1/basket/{id}")
    public Long update(@PathVariable Long id, @RequestBody BasketUpdateRequestDto requestDto) {
        return basketService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/basket/{id}")
    public Long delete(@PathVariable Long id) {
        basketService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/basket/{id}")
    public BasketResponseDto findById(@PathVariable Long id) {
        return basketService.findById(id);
    }

    @GetMapping("/api/v1/basket/list")
    public List<BasketListResponseDto> findAll() {
        return basketService.findAllDesc();
    }
}
