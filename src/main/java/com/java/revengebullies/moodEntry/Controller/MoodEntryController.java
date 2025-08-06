package com.java.revengebullies.moodEntry.Controller;

import com.java.revengebullies.moodEntry.DTO.MoodCreateRequest;
import com.java.revengebullies.moodEntry.DTO.MoodResponse;
import com.java.revengebullies.moodEntry.DTO.MoodUpdateRequest;
import com.java.revengebullies.moodEntry.service.MoodEntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/moods")
public class MoodEntryController {

    private final MoodEntryService service;

    public MoodEntryController(MoodEntryService service) {
        this.service = service;
    }

    @Operation(summary = "Crear mood del día (único por fecha)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MoodResponse create(@Validated @RequestBody MoodCreateRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Listar moods (opcional filtrar por rango de fechas)")
    @GetMapping
    public Page<MoodResponse> list(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "Desde (incluye)") LocalDate from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "Hasta (incluye)") LocalDate to,
            @ParameterObject Pageable pageable
    ) {
        return service.list(from, to, pageable == null ? PageRequest.of(0, 20, Sort.by("date").descending()) : pageable);
    }

    @Operation(summary = "Obtener un mood por id")
    @GetMapping("/{id}")
    public MoodResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @Operation(summary = "Actualizar parcialmente un mood")
    @PatchMapping("/{id}")
    public MoodResponse patch(@PathVariable Long id, @Validated @RequestBody MoodUpdateRequest request) {
        return service.patch(id, request);
    }

    @Operation(summary = "Eliminar un mood")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}