package com.chat.aj.unote.Notes.Entity;

import com.chat.aj.unote.Accounts.Entity.Accounts;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;           // "Calculus II"

    @Column(nullable = false)
    private String professor;      // Just a string

    @Column(nullable = false)
    private Integer year;          // 2025

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Accounts user;

    @OneToMany(mappedBy = "myClass", cascade = CascadeType.ALL)
    private List<Unit> units = new ArrayList<>();
}
