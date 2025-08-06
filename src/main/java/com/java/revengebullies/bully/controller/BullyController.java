package com.java.revengebullies.bully.controller;

import com.java.revengebullies.bully.dto.BullyDTO;
import com.java.revengebullies.bully.dto.ResponseBullyDTO;
import com.java.revengebullies.bully.dto.UpdateBullyDTO;
import com.java.revengebullies.bully.enums.Roles;
import com.java.revengebullies.bully.service.BullyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bullies")
@RequiredArgsConstructor
public class BullyController {

    private final BullyService service;

    @PostMapping
    public ResponseEntity<ResponseBullyDTO> create(@RequestBody @Valid BullyDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseBullyDTO get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    public List<ResponseBullyDTO> list(@RequestParam(required = false) Long cliqueId,
                                       @RequestParam(required = false) Roles role) {
        return service.list(cliqueId, role);
    }

    @PatchMapping("/{id}")
    public ResponseBullyDTO update(@PathVariable Long id, @RequestBody UpdateBullyDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
