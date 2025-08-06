package com.java.revengebullies.media.controller;

import com.java.revengebullies.media.dto.MediaDTO;
import com.java.revengebullies.media.dto.ResponseMediaDTO;
import com.java.revengebullies.media.service.MediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService service;

    @PostMapping
    public ResponseEntity<ResponseMediaDTO> add(@RequestBody @Valid MediaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(dto));
    }

    @GetMapping("/by-plan/{planId}")
    public List<ResponseMediaDTO> listByPlan(@PathVariable("planId") Long planId) {
        return service.listByPlan(planId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}