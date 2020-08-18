package com.moskhu.service.posts;

import com.moskhu.domain.posts.Status;
import com.moskhu.domain.posts.StatusRepository;
import com.moskhu.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StatusService {
    private final StatusRepository statusRepository;

    @Transactional
    public Long save(StatusSaveRequestDto requestDto) {
        return statusRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, StatusUpdateRequestDto requestDto) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        status.update(requestDto.getSeller_id(), requestDto.isOn_off());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        statusRepository.delete(status);
    }

    @Transactional(readOnly = true)
    public StatusResponseDto findById(Long id) {
        Status entity = statusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));

        return new StatusResponseDto(entity);
    }

    @Transactional
    public boolean existsById (Long id) {
        return statusRepository.existsById(id);
    }
}
