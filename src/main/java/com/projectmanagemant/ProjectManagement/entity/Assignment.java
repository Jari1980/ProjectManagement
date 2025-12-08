package com.projectmanagemant.ProjectManagement.entity;

import com.projectmanagemant.ProjectManagement.model.AssignmentListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "assignments")
@EntityListeners(AssignmentListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "task_id", unique = true)
    private Task task;

    @Embedded
    private Developer developer;

    private LocalDate startDate;
}
