package com.java.revengebullies.bully.models;
import com.java.revengebullies.revengePlan.models.RevengePlan;

import com.java.revengebullies.bully.enums.Roles;
import com.java.revengebullies.clique.models.Clique;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name="bully")
@Data()
@NoArgsConstructor
public class Bully {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String name;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles highSchoolRole;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clique_id",nullable = false)
    private Clique clique;

    @Column(nullable = false)
    private String bullyingReason;

    @Column(nullable = false)
    @Min(1)
    @Max(10)
    private int levelOfAnnoyance;

    @OneToMany(mappedBy = "bully", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RevengePlan> revengePlans = new ArrayList<>();
}
