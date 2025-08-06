package com.java.revengebullies.media.service;

import com.java.revengebullies.Exceptions.NotFoundExceptions;
import com.java.revengebullies.media.dto.MediaDTO;
import com.java.revengebullies.media.dto.ResponseMediaDTO;
import com.java.revengebullies.media.models.Media;
import com.java.revengebullies.media.repository.MediaRepository;
import com.java.revengebullies.revengePlan.models.RevengePlan;
import com.java.revengebullies.revengePlan.repository.RevengePlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository repo;
    private final RevengePlanRepository planRepo;

    public ResponseMediaDTO add(MediaDTO dto) {
        RevengePlan plan = planRepo.findById(dto.getRevengePlanId())
                .orElseThrow(() -> new NotFoundExceptions("RevengePlan not found"));

        Media m = new Media();
        m.setRevengePlan(plan);
        m.setType(dto.getType());
        m.setUrl(dto.getUrl());
        m.setCaption(dto.getCaption());

        return toResponse(repo.save(m));
    }

    @Transactional(readOnly = true)
    public List<ResponseMediaDTO> listByPlan(Long revengePlanId) {
        if (!planRepo.existsById(revengePlanId)) throw new NotFoundExceptions("RevengePlan not found");
        return repo.findByRevengePlanId(revengePlanId).stream().map(this::toResponse).toList();
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundExceptions("Media not found");
        repo.deleteById(id);
    }

    private ResponseMediaDTO toResponse(Media e) {
        return ResponseMediaDTO.builder()
                .id(e.getId())
                .type(e.getType().name())
                .url(e.getUrl())
                .caption(e.getCaption())
                .build();
    }
}
