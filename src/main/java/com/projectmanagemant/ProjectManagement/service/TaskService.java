package com.projectmanagemant.ProjectManagement.service;

import com.projectmanagemant.ProjectManagement.DTO.TaskRequestDTO;
import com.projectmanagemant.ProjectManagement.DTO.TaskResponseDTO;
import com.projectmanagemant.ProjectManagement.DTO.TaskUpdateRequestDTO;
import com.projectmanagemant.ProjectManagement.entity.Project;
import com.projectmanagemant.ProjectManagement.entity.Task;
import com.projectmanagemant.ProjectManagement.repo.ProjectRepository;
import com.projectmanagemant.ProjectManagement.repo.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository){
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public TaskResponseDTO createTask(TaskRequestDTO request) {

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .priority(request.getPriority())
                .project(project)
                .build();

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TaskResponseDTO getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return mapToResponse(task);
    }

    public TaskResponseDTO updateTask(Long id, TaskUpdateRequestDTO request) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.setTitle(request.getTitle());
        task.setPriority(request.getPriority());

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (task.getAssignment() != null) {
            throw new IllegalStateException("Cannot delete a task that has an assignment");
        }

        taskRepository.delete(task);
    }

    private TaskResponseDTO mapToResponse(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .priority(task.getPriority())
                .projectId(task.getProject().getId())
                .assigned(task.getAssignment() != null)
                .build();
    }
}
