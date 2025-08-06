package com.java.revengebullies.clique.service;

import com.java.revengebullies.Exceptions.NotFoundExceptions;
import com.java.revengebullies.bully.dto.ResponseBullyDTO;
import com.java.revengebullies.clique.dto.CliqueDTO;
import com.java.revengebullies.clique.dto.CliqueResponseDTO;
import com.java.revengebullies.clique.dto.CliqueUpdateDTO;
import com.java.revengebullies.clique.models.Clique;
import com.java.revengebullies.clique.repository.CliqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CliqueService {

    private final CliqueRepository repo;

    public CliqueResponseDTO create(CliqueDTO dto) {
        Clique c = new Clique();
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        return toResponse(repo.save(c));
    }

    @Transactional(readOnly = true)
    public CliqueResponseDTO get(Long id) {
        Clique c = repo.findById(id).orElseThrow(() -> new NotFoundExceptions("Clique not found"));
        return toResponse(c);
    }

    @Transactional(readOnly = true)
    public List<CliqueResponseDTO> list() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public CliqueResponseDTO update(Long id, CliqueUpdateDTO dto) {
        Clique c = repo.findById(id).orElseThrow(() -> new NotFoundExceptions("Clique not found"));
        if (dto.getName() != null) c.setName(dto.getName());
        if (dto.getDescription() != null) c.setDescription(dto.getDescription());
        return toResponse(repo.save(c));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundExceptions("Clique not found");
        repo.deleteById(id);
    }

    private CliqueResponseDTO toResponse(Clique e) {
        return CliqueResponseDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .bullies(
                        e.getBullies() == null ? List.of()
                                : e.getBullies().stream().map(b -> ResponseBullyDTO.builder()
                                        .id(b.getId())
                                        .name(b.getName())
                                        .nickname(b.getNickname())
                                        .highSchoolRole(b.getHighSchoolRole().name())
                                        .cliqueName(e.getName())
                                        .levelOfAnnoyance(b.getLevelOfAnnoyance())
                                        .build())
                                .toList()
                )
                .build();
    }
}