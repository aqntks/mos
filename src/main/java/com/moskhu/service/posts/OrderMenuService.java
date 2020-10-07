package com.moskhu.service.posts;

import com.moskhu.domain.posts.OrderMenu;
import com.moskhu.domain.posts.OrderMenuRepository;
import com.moskhu.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderMenuService {
    private final OrderMenuRepository orderMenuRepository;

    @Transactional
    public Long save(OrderMenuSaveRequestDto requestDto) {
        return orderMenuRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, OrderMenuUpdateRequestDto requestDto) {
        OrderMenu orderMenu = orderMenuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        orderMenu.update(requestDto.getMenus(), requestDto.getTotal());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        OrderMenu orderMenu = orderMenuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        orderMenuRepository.delete(orderMenu);
    }

    @Transactional(readOnly = true)
    public OrderMenuResponseDto findById(Long id) {
        OrderMenu entity = orderMenuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        return new OrderMenuResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<OrderMenuListResponseDto> findAllDesc() {
        return orderMenuRepository.findAllDesc().stream()
                .map(OrderMenuListResponseDto::new)
                .collect(Collectors.toList());
    }
}
