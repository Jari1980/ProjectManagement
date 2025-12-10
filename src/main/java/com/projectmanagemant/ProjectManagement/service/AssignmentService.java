package com.projectmanagemant.ProjectManagement.service;

import com.projectmanagemant.ProjectManagement.DTO.AssignmentRequestDTO;
import com.projectmanagemant.ProjectManagement.DTO.AssignmentResponseDTO;
import com.projectmanagemant.ProjectManagement.DTO.AssignmentUpdateRequestDTO;
import com.projectmanagemant.ProjectManagement.entity.Assignment;
import com.projectmanagemant.ProjectManagement.entity.Developer;
import com.projectmanagemant.ProjectManagement.entity.Task;
import com.projectmanagemant.ProjectManagement.repo.AssignmentRepository;
import com.projectmanagemant.ProjectManagement.repo.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, TaskRepository taskRepository){
        this.assignmentRepository = assignmentRepository;
        this.taskRepository = taskRepository;
    }

    public AssignmentResponseDTO createAssignment(AssignmentRequestDTO request){

        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(() -> new IllegalArgumentException("No such task"));

        Developer developer = Developer.builder()
                .firstName(request.getDeveloperFirstName())
                .lastName(request.getDeveloperLastName())
                .email(request.getDeveloperEmail())
                .build();

        Assignment assignment = Assignment.builder()
                .task(task)
                .developer(developer)
                .startDate(request.getStartDate())
                .build();

        Assignment saved = assignmentRepository.save(assignment);

        return mapToResponse(saved);
    }

    public List<AssignmentResponseDTO> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public AssignmentResponseDTO getAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        return mapToResponse(assignment);
    }

    private AssignmentResponseDTO mapToResponse(Assignment assignment) {
        return AssignmentResponseDTO.builder()
                .id(assignment.getId())
                .taskId(assignment.getTask().getId())
                .developerFullName(assignment.getDeveloper().getFirstName()
                        + " " + assignment.getDeveloper().getLastName())
                .developerEmail(assignment.getDeveloper().getEmail())
                .startDate(assignment.getStartDate())
                .build();
    }

    public AssignmentResponseDTO updateAssignment(Long id, AssignmentUpdateRequestDTO request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        Developer dev = assignment.getDeveloper();

        dev.setFirstName(request.getDeveloperFirstName());
        dev.setLastName(request.getDeveloperLastName());
        dev.setEmail(request.getDeveloperEmail());

        assignment.setStartDate(request.getStartDate());

        Assignment updated = assignmentRepository.save(assignment);
        return mapToResponse(updated);
    }

    public void deleteAssignment(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Assignment not found");
        }
        assignmentRepository.deleteById(id);
    }
}
