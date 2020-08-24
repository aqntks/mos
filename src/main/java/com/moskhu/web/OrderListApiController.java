package com.moskhu.web;

import com.moskhu.web.dto.*;
import com.moskhu.service.posts.OrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderListApiController {

    private final OrderListService orderListService;

    @PostMapping("/api/v1/orderList")
    public Long save(@RequestBody OrderListSaveRequestDto requestDto) {
        return orderListService.save(requestDto);
    }

    @PutMapping("/api/v1/orderList/{id}")
    public Long update(@PathVariable Long id, @RequestBody OrderListUpdateRequestDto requestDto) {
        return orderListService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/orderList/{id}")
    public Long delete(@PathVariable Long id) {
        orderListService.delete(id);
        return id;
    }

    @DeleteMapping("/api/v1/orderList/consumer/{consumerId}")
    public Integer consumerDelete(@PathVariable Integer consumerId) {
        List<OrderListListResponseDto> temp = orderListService.findAllConsumerIdAsc();
        for(OrderListListResponseDto o : temp){
            if(o.getConsumerId() == consumerId)
                orderListService.delete(o.getId());
        }
        return consumerId;
    }

    @GetMapping("/api/v1/orderList/{id}")
    public OrderListResponseDto findById(@PathVariable Long id) {
        return orderListService.findById(id);
    }

    @GetMapping("/api/v1/orderList/list")
    public List<OrderListListResponseDto> findAll() {
        return orderListService.findAllDesc();
    }
}
