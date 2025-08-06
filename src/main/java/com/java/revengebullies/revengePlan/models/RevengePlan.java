package com.java.revengebullies.revengePlan.models;

import com.java.revengebullies.bully.models.Bully;
import com.java.revengebullies.media.models.Media;
import com.java.revengebullies.revengePlan.enums.Success;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name="revengePlan")
@Data
@NoArgsConstructor
public class RevengePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false)
    private String title;

    @Lob
    @Column(nullable = false,unique = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bully_id",nullable = false)
    private Bully bully;

    @Column(nullable = false)
    private Boolean isExecuted = false;

    @Column(nullable = false)
    private LocalDateTime datePlanned;

    @Enumerated(EnumType.STRING)
    @Column(name = "success_level")
    private Success successLevel;

    @OneToMany(mappedBy = "revengePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList = new ArrayList<>();
}
