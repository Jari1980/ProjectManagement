package com.projectmanagemant.ProjectManagement.entity;

import com.projectmanagemant.ProjectManagement.model.Priority;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Project project;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL)
    private Assignment assignment;
}
