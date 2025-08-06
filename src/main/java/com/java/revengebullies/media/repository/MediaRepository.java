package com.java.revengebullies.media.repository;

import com.java.revengebullies.media.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByRevengePlanId(Long RevengePlanId);
}
