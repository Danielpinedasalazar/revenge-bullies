package com.java.revengebullies.moodEntry.repository;

import com.java.revengebullies.moodEntry.model.MoodEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {

    boolean existsByDate(LocalDate date);

    boolean existsByDateAndIdNot(LocalDate date, Long id);

    Page<MoodEntry> findByDateBetween(LocalDate from, LocalDate to, Pageable pageable);
}
