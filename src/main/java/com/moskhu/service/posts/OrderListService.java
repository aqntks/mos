package com.moskhu.service.posts;

import com.moskhu.domain.posts.OrderList;
import com.moskhu.domain.posts.OrderListRepository;
import com.moskhu.web.dto.OrderListListResponseDto;
import com.moskhu.web.dto.OrderListResponseDto;
import com.moskhu.web.dto.OrderListSaveRequestDto;
import com.moskhu.web.dto.OrderListUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderListService {
    private final OrderListRepository orderListRepository;

    @Transactional
    public Long save(OrderListSaveRequestDto requestDto) {
        return orderListRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, OrderListUpdateRequestDto requestDto) {
        OrderList orderList = orderListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        orderList.update(requestDto.getConsumerId(), requestDto.getMenuId());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        OrderList orderList = orderListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        orderListRepository.delete(orderList);
    }

    @Transactional(readOnly = true)
    public OrderListResponseDto findById(Long id) {
        OrderList entity = orderListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        return new OrderListResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<OrderListListResponseDto> findAllDesc() {
        return orderListRepository.findAllDesc().stream()
                .map(OrderListListResponseDto::new)
                .collect(Collectors.toList());
    }
}
