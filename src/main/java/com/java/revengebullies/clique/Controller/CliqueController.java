package com.java.revengebullies.clique.Controller;

import com.java.revengebullies.clique.dto.CliqueDTO;
import com.java.revengebullies.clique.dto.CliqueResponseDTO;
import com.java.revengebullies.clique.dto.CliqueUpdateDTO;
import com.java.revengebullies.clique.service.CliqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliques")
@RequiredArgsConstructor
public class CliqueController {

    private final CliqueService service;

    @PostMapping
    public ResponseEntity<CliqueResponseDTO> create(@RequestBody @Valid CliqueDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public CliqueResponseDTO get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    public List<CliqueResponseDTO> list() { return service.list(); }

    @PatchMapping("/{id}")
    public CliqueResponseDTO update(@PathVariable Long id, @RequestBody CliqueUpdateDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}