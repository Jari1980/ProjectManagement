package com.projectmanagemant.ProjectManagement.service;

import com.projectmanagemant.ProjectManagement.DTO.ProjectRequestDTO;
import com.projectmanagemant.ProjectManagement.DTO.ProjectResponseDTO;
import com.projectmanagemant.ProjectManagement.DTO.ProjectUpdateRequestDTO;
import com.projectmanagemant.ProjectManagement.entity.Project;
import com.projectmanagemant.ProjectManagement.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public ProjectResponseDTO createProject(ProjectRequestDTO request) {

        Project project = Project.builder()
                .name(request.getName())
                .build();

        Project saved = projectRepository.save(project);
        return mapToResponse(saved);
    }

    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProjectResponseDTO getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        return mapToResponse(project);
    }

    public ProjectResponseDTO updateProject(Long id, ProjectUpdateRequestDTO request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        project.setName(request.getName());

        Project updated = projectRepository.save(project);
        return mapToResponse(updated);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        if (!project.getTasks().isEmpty()) {
            throw new IllegalStateException("Cannot delete a project that already has tasks");
        }

        projectRepository.delete(project);
    }

    private ProjectResponseDTO mapToResponse(Project project) {
        return ProjectResponseDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .createdAt(project.getCreatedAt())
                .totalTasks(project.getTasks().size())
                .build();
    }
}
