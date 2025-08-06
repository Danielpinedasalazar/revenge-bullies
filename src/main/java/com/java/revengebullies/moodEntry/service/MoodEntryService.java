package com.java.revengebullies.moodEntry.service;

import com.java.revengebullies.moodEntry.DTO.MoodCreateRequest;
import com.java.revengebullies.moodEntry.DTO.MoodResponse;
import com.java.revengebullies.moodEntry.DTO.MoodUpdateRequest;
import com.java.revengebullies.moodEntry.model.MoodEntry;
import com.java.revengebullies.moodEntry.repository.MoodEntryRepository;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@Transactional
public class MoodEntryService {

    private final MoodEntryRepository repository;

    public MoodEntryService(MoodEntryRepository repository) {
        this.repository = repository;
    }

    public MoodResponse create(MoodCreateRequest req) {
        if (repository.existsByDate(req.date())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un registro para esa fecha");
        }
        MoodEntry saved = repository.save(MoodEntry.builder()
                .date(req.date())
                .moodLevel(req.moodLevel())
                .note(req.note())
                .build());
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<MoodResponse> list(LocalDate from, LocalDate to, Pageable pageable) {
        if (from != null && to != null) {
            return repository.findByDateBetween(from, to, pageable).map(this::toResponse);
        }
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public MoodResponse get(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mood no encontrado"));
    }

    public MoodResponse patch(Long id, MoodUpdateRequest req) {
        MoodEntry entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mood no encontrado"));

        if (req.date() != null) {
            if (repository.existsByDateAndIdNot(req.date(), id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un registro para esa fecha");
            }
            entity.setDate(req.date());
        }
        if (req.moodLevel() != null) entity.setMoodLevel(req.moodLevel());
        if (req.note() != null) entity.setNote(req.note());

        return toResponse(entity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mood no encontrado");
        }
        repository.deleteById(id);
    }

    private MoodResponse toResponse(MoodEntry e) {
        return new MoodResponse(e.getId(), e.getDate(), e.getMoodLevel(), e.getNote());
    }
}