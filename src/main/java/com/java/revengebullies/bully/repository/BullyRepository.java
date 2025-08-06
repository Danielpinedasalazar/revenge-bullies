package com.java.revengebullies.bully.repository;

import com.java.revengebullies.bully.enums.Roles;
import com.java.revengebullies.bully.models.Bully;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BullyRepository extends JpaRepository<Bully, Long> {
    List<Bully> findByCliqueId(Long cliqueId);
    List<Bully> findByHighSchoolRole(Roles role);
}
