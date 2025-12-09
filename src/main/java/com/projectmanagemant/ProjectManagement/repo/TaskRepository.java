package com.projectmanagemant.ProjectManagement.repo;

import com.projectmanagemant.ProjectManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
