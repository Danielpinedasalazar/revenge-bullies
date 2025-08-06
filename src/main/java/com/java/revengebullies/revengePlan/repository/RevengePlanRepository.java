package com.java.revengebullies.revengePlan.repository;

import com.java.revengebullies.revengePlan.models.RevengePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevengePlanRepository extends JpaRepository<RevengePlan, Long> {
    List<RevengePlan> findByBullyId(Long BullyId);
}
