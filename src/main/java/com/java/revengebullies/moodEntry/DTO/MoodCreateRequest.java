package com.java.revengebullies.moodEntry.DTO;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record MoodCreateRequest(
        @NotNull @Schema(example = "2025-08-15") LocalDate date,
        @Min(1) @Max(10) @Schema(example = "9") int moodLevel,
        @Size(max = 1000) @Schema(example = "Se sintió glorioso publicar el meme")
        String note
) { }