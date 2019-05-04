package com.damian.wnukowski.projectboard.web;

import com.damian.wnukowski.projectboard.entity.Project;
import com.damian.wnukowski.projectboard.service.ProjectService;
import com.damian.wnukowski.projectboard.util.BindingResultErrorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class ProjectController {
    private final ProjectService projectService;
    private final BindingResultErrorMapper bindingResultErrorMapper;

    @Autowired
    public ProjectController(ProjectService projectService, BindingResultErrorMapper bindingResultErrorMapper) {
        this.projectService = projectService;
        this.bindingResultErrorMapper = bindingResultErrorMapper;
    }

    @PostMapping("")
    public ResponseEntity<?> addProjectTaskToBoard(@Valid @RequestBody Project project, BindingResult result){
        ResponseEntity<?> res = bindingResultErrorMapper.mapBindingResults(result);
        if(res != null) return res;

        Project newProject = projectService.saveOrUpdateProjectTask(project);

        return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public Iterable<Project> getAllProjectTasks(){
        return projectService.findAllProjectTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectTaskById(@PathVariable Long id){
        Project pt = projectService.findById(id);
        if(pt==null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(pt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable Long id){
        try {
            projectService.delete(id);
        }catch (EmptyResultDataAccessException ex){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
