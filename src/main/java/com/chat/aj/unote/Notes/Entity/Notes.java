package com.chat.aj.unote.Notes.Entity;


import com.chat.aj.unote.Accounts.Entity.Accounts;
import com.chat.aj.unote.Notes.NoteType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoteType type;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String fileUrl;

    @Column
    private String fileName;      // original filename

    @Column
    private String fileType;      // MIME type

    @Column
    private Long fileSize;        // in bytes

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true) //TODO change to false once verified
    private Accounts user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
