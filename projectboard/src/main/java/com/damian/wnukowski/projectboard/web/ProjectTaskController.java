package com.damian.wnukowski.projectboard.web;

import com.damian.wnukowski.projectboard.entities.ProjectTask;
import com.damian.wnukowski.projectboard.service.ProjectTaskService;
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
@CrossOrigin
public class ProjectTaskController {
    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("")
    public ResponseEntity<?> addProjectTaskToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result){

        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        ProjectTask newProjectTask = projectTaskService.saveOrUpdateProjectTask(projectTask);

        return new ResponseEntity<ProjectTask>(newProjectTask, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<ProjectTask> getAllProjectTasks(){
        return projectTaskService.findAllProjectTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectTaskById(@PathVariable Long id){
        ProjectTask pt = projectTaskService.findById(id);
        if(pt==null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(pt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable Long id){
        try {
            projectTaskService.delete(id);
        }catch (EmptyResultDataAccessException ex){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
