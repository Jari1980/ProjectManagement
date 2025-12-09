package com.projectmanagemant.ProjectManagement.DTO;

import com.projectmanagemant.ProjectManagement.model.Priority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDTO {
    @NonNull
    private String title;
    @NonNull
    private Priority priority;
    @NonNull
    private Long projectId;
}