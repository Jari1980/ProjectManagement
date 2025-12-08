package com.projectmanagemant.ProjectManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer {
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String email;
}
