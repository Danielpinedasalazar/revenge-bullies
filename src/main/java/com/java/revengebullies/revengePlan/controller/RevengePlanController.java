package com.java.revengebullies.revengePlan.controller;

import com.java.revengebullies.revengePlan.dto.ResponseRevengePlanDTO;
import com.java.revengebullies.revengePlan.dto.RevengePlanDTO;
import com.java.revengebullies.revengePlan.dto.UpdateRevengePlanDTO;
import com.java.revengebullies.revengePlan.service.RevengePlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revenge-plans")
@RequiredArgsConstructor
public class RevengePlanController {

    private final RevengePlanService service;

    @PostMapping
    public ResponseEntity<ResponseRevengePlanDTO> create(@RequestBody @Valid RevengePlanDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseRevengePlanDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/by-bully/{bullyId}")
    public List<ResponseRevengePlanDTO> listByBully(@PathVariable Long bullyId) {
        return service.listByBully(bullyId);
    }

    @PatchMapping("/{id}")
    public ResponseRevengePlanDTO update(@PathVariable Long id, @RequestBody UpdateRevengePlanDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
