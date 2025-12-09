package com.projectmanagemant.ProjectManagement.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUpdateRequestDTO {
    @NonNull
    private String name;
}
