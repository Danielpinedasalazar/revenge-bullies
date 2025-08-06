package com.java.revengebullies.moodEntry.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "mood_entries",
        uniqueConstraints = @UniqueConstraint(name = "uk_mood_date", columnNames = "date")
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @Min(1) @Max(10)
    private int moodLevel;

    @Size(max = 1000)
    @Column(length = 1000)
    private String note;
}