package com.java.revengebullies.moodEntry.DTO;

import java.time.LocalDate;

public record MoodResponse(
        Long id,
        LocalDate date,
        int moodLevel,
        String note
) { }