package com.java.revengebullies.bully.service;

import com.java.revengebullies.Exceptions.NotFoundExceptions;
import com.java.revengebullies.bully.dto.BullyDTO;
import com.java.revengebullies.bully.dto.ResponseBullyDTO;
import com.java.revengebullies.bully.dto.UpdateBullyDTO;
import com.java.revengebullies.bully.enums.Roles;
import com.java.revengebullies.bully.models.Bully;
import com.java.revengebullies.bully.repository.BullyRepository;
import com.java.revengebullies.clique.models.Clique;
import com.java.revengebullies.clique.repository.CliqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BullyService {

    private final BullyRepository repo;
    private final CliqueRepository cliqueRepo;

    public ResponseBullyDTO create(BullyDTO dto) {
        Clique clique = cliqueRepo.findById(dto.getCliqueId())
                .orElseThrow(() -> new NotFoundExceptions("Clique not found"));

        Bully e = new Bully();
        e.setName(dto.getName());
        e.setNickname(dto.getNickname());
        e.setHighSchoolRole(dto.getHighSchoolRole());
        e.setClique(clique);
        e.setBullyingReason(dto.getBullyingReason());
        e.setLevelOfAnnoyance(dto.getLevelOfAnnoyance());

        return toResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public ResponseBullyDTO get(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundExceptions("Bully not found"));
    }

    @Transactional(readOnly = true)
    public List<ResponseBullyDTO> list(Long cliqueId, Roles role) {
        if (cliqueId != null) return repo.findByCliqueId(cliqueId).stream().map(this::toResponse).toList();
        if (role != null) return repo.findByHighSchoolRole(role).stream().map(this::toResponse).toList();
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public ResponseBullyDTO update(Long id, UpdateBullyDTO dto) {
        Bully e = repo.findById(id).orElseThrow(() -> new NotFoundExceptions("Bully not found"));

        if (dto.getName() != null) e.setName(dto.getName());
        if (dto.getNickname() != null) e.setNickname(dto.getNickname());
        if (dto.getHighSchoolRole() != null) e.setHighSchoolRole(dto.getHighSchoolRole());
        if (dto.getCliqueId() != null) {
            Clique clique = cliqueRepo.findById(dto.getCliqueId())
                    .orElseThrow(() -> new NotFoundExceptions("Clique not found"));
            e.setClique(clique);
        }
        if (dto.getBullyingReason() != null) e.setBullyingReason(dto.getBullyingReason());
        if (dto.getLevelOfAnnoyance() != null) e.setLevelOfAnnoyance(dto.getLevelOfAnnoyance());

        return toResponse(repo.save(e));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundExceptions("Bully not found");
        repo.deleteById(id);
    }

    private ResponseBullyDTO toResponse(Bully e) {
        return ResponseBullyDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .nickname(e.getNickname())
                .highSchoolRole(e.getHighSchoolRole().name())
                .cliqueName(e.getClique() != null ? e.getClique().getName() : null)
                .levelOfAnnoyance(e.getLevelOfAnnoyance())
                .build();
    }
}
