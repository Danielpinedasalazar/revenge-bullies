package com.java.revengebullies.media.models;

import com.java.revengebullies.media.enums.Type;
import com.java.revengebullies.revengePlan.models.RevengePlan;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name="media")
@Data
@NoArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reveng_plan",nullable = false)
    private RevengePlan revengePlan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private Type type;

    @Column(nullable = false, length = 1024)
    private String url;

    @Column(nullable = false, length = 255)
    private String caption;
}
