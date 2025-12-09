package com.projectmanagemant.ProjectManagement.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentUpdateRequestDTO {
    @NonNull
    private String developerFirstName;
    @NonNull
    private String developerLastName;
    @NonNull
    private String developerEmail;
    @NonNull
    private LocalDate startDate;
}
