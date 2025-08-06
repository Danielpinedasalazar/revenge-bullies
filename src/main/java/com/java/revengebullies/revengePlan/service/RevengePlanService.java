package com.java.revengebullies.revengePlan.service;

import com.java.revengebullies.Exceptions.NotFoundExceptions;
import com.java.revengebullies.bully.models.Bully;
import com.java.revengebullies.bully.repository.BullyRepository;
import com.java.revengebullies.revengePlan.dto.ResponseRevengePlanDTO;
import com.java.revengebullies.revengePlan.dto.RevengePlanDTO;
import com.java.revengebullies.revengePlan.dto.UpdateRevengePlanDTO;
import com.java.revengebullies.revengePlan.models.RevengePlan;
import com.java.revengebullies.revengePlan.repository.RevengePlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RevengePlanService {

    private final RevengePlanRepository repo;
    private final BullyRepository bullyRepo;

    public ResponseRevengePlanDTO create(RevengePlanDTO dto) {
        Bully bully = bullyRepo.findById(dto.getBullyId())
                .orElseThrow(() -> new NotFoundExceptions("Bully not found"));

        RevengePlan e = new RevengePlan();
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setBully(bully);
        e.setDatePlanned(dto.getDatePlanned());
        e.setIsExecuted(false);
        e.setSuccessLevel(null);

        return toResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public ResponseRevengePlanDTO get(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundExceptions("RevengePlan not found"));
    }

    @Transactional(readOnly = true)
    public List<ResponseRevengePlanDTO> listByBully(Long bullyId) {
        if (!bullyRepo.existsById(bullyId)) throw new NotFoundExceptions("Bully not found");
        return repo.findByBullyId(bullyId).stream().map(this::toResponse).toList();
    }

    public ResponseRevengePlanDTO update(Long id, UpdateRevengePlanDTO dto) {
        RevengePlan e = repo.findById(id).orElseThrow(() -> new NotFoundExceptions("RevengePlan not found"));

        if (dto.getTitle() != null) e.setTitle(dto.getTitle());
        if (dto.getDescription() != null) e.setDescription(dto.getDescription());
        if (dto.getDatePlanned() != null) e.setDatePlanned(dto.getDatePlanned());
        if (dto.getIsExecuted() != null) e.setIsExecuted(dto.getIsExecuted());
        if (dto.getSuccessLevel() != null) e.setSuccessLevel(dto.getSuccessLevel());

        return toResponse(repo.save(e));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundExceptions("RevengePlan not found");
        repo.deleteById(id);
    }

    private ResponseRevengePlanDTO toResponse(RevengePlan e) {
        return ResponseRevengePlanDTO.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .bullyName(e.getBully() != null ? e.getBully().getName() : null)
                .isExecuted(e.getIsExecuted())
                .datePlanned(e.getDatePlanned())
                .successLevel(e.getSuccessLevel() == null ? null : e.getSuccessLevel().name())
                .build();
    }
}