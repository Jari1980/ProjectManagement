package com.projectmanagemant.ProjectManagement.DTO;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentRequestDTO {
    @NonNull
    private Long taskId;
    @NonNull
    private String developerFirstName;
    @NonNull
    private String developerLastName;
    @NonNull
    private String developerEmail;
    @NonNull
    private LocalDate startDate;
}
