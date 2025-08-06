package com.java.revengebullies.moodEntry.DTO;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record MoodUpdateRequest(
        @Schema(example = "2025-08-16") LocalDate date,   // opcional en PATCH
        @Min(1) @Max(10) @Schema(example = "7") Integer moodLevel,
        @Size(max = 1000) @Schema(example = "Hoy más tranqui") String note
) { }