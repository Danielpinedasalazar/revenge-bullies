package com.java.revengebullies.clique.models;

import com.java.revengebullies.bully.models.Bully;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name="clique")
@Data
@NoArgsConstructor
public class Clique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "clique", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bully> bullies = new ArrayList<>();
}
