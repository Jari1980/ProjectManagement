package com.projectmanagemant.ProjectManagement.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponseDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private int totalTasks;
}
