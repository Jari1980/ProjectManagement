package com.projectmanagemant.ProjectManagement.controller;

import com.projectmanagemant.ProjectManagement.DTO.AssignmentRequestDTO;
import com.projectmanagemant.ProjectManagement.DTO.AssignmentResponseDTO;
import com.projectmanagemant.ProjectManagement.DTO.AssignmentUpdateRequestDTO;
import com.projectmanagemant.ProjectManagement.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService){
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<AssignmentResponseDTO> create(@RequestBody AssignmentRequestDTO request) {
        AssignmentResponseDTO response = assignmentService.createAssignment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponseDTO>> getAll() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignment(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> update(@PathVariable Long id, @RequestBody AssignmentUpdateRequestDTO request) {
        AssignmentResponseDTO response = assignmentService.updateAssignment(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
