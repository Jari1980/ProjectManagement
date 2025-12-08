package com.projectmanagemant.ProjectManagement.model;

import com.projectmanagemant.ProjectManagement.entity.Assignment;
import com.projectmanagemant.ProjectManagement.entity.Task;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

//Raising priority when assignment is created
public class AssignmentListener {
    @PrePersist
    public void beforeSave(Assignment assignment) {
        Task task = assignment.getTask();
        if (task != null) {
            task.setPriority(Priority.HIGH);
        }
    }
}
