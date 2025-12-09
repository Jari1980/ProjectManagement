package com.projectmanagemant.ProjectManagement.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentResponseDTO {
    private Long id;
    private Long taskId;
    private String developerFullName;
    private String developerEmail;
    private LocalDate startDate;
}
