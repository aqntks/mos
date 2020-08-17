package com.moskhu.web;

import com.moskhu.service.posts.StatusService;
import com.moskhu.web.dto.*;
import com.moskhu.service.posts.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StatusApiController {

    private final StatusService statusService;

    @PostMapping("/api/v1/status")
    public Long save(@RequestBody StatusSaveRequestDto requestDto) {
        return statusService.save(requestDto);
    }

    @PutMapping("/api/v1/status/{id}")
    public Long update(@PathVariable Long id, @RequestBody StatusUpdateRequestDto requestDto) {
        return statusService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/status/{id}")
    public Long delete(@PathVariable Long id) {
        statusService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/status/{id}")
    public StatusResponseDto findById(@PathVariable Long id) {
        return statusService.findById(id);
    }
}
