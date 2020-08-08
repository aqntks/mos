package com.moskhu.service.posts;

import com.moskhu.domain.posts.Menu;
import com.moskhu.domain.posts.MenuRepository;
import com.moskhu.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public Long save(MenuSaveRequestDto requestDto) {
        return menuRepository.save(requestDto.toEntity()).getMenuId();
    }

    @Transactional
    public Long update(Long menuId, MenuUpdateRequestDto requestDto) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + menuId));

        menu.update(requestDto.getSellerId(), requestDto.getMenuName(), requestDto.getMenuPrice(), requestDto.getMenuDescription());

        return menuId;
    }

    @Transactional
    public void delete (Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + menuId));

        menuRepository.delete(menu);
    }

    @Transactional(readOnly = true)
    public MenuResponseDto findById(Long menuId) {
        Menu entity = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + menuId));

        return new MenuResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<MenuListResponseDto> findAllDesc() {
        return menuRepository.findAllDesc().stream()
                .map(MenuListResponseDto::new)
                .collect(Collectors.toList());
    }
}
