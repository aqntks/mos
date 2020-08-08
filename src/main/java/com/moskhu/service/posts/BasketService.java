package com.moskhu.service.posts;

import com.moskhu.domain.posts.Basket;
import com.moskhu.domain.posts.BasketRepository;
import com.moskhu.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BasketService {
    private final BasketRepository basketRepository;

    @Transactional
    public Long save(BasketSaveRequestDto requestDto) {
        return basketRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BasketUpdateRequestDto requestDto) {
        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        basket.update(requestDto.getConsumerId(), requestDto.getMenuId());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        basketRepository.delete(basket);
    }

    @Transactional(readOnly = true)
    public BasketResponseDto findById(Long id) {
        Basket entity = basketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        return new BasketResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BasketListResponseDto> findAllDesc() {
        return basketRepository.findAllDesc().stream()
                .map(BasketListResponseDto::new)
                .collect(Collectors.toList());
    }
}
