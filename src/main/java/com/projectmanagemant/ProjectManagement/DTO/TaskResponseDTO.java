package com.projectmanagemant.ProjectManagement.DTO;

import com.projectmanagemant.ProjectManagement.model.Priority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {
    private Long id;
    private String title;
    private Priority priority;
    private Long projectId;
    private boolean assigned; // helpful UI info
}
