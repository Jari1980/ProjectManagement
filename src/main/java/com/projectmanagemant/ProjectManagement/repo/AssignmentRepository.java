package com.projectmanagemant.ProjectManagement.repo;

import com.projectmanagemant.ProjectManagement.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
