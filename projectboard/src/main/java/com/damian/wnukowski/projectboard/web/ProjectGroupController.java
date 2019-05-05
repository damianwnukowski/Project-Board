package com.damian.wnukowski.projectboard.web;

import com.damian.wnukowski.projectboard.entity.ProjectGroup;
import com.damian.wnukowski.projectboard.service.ProjectGroupService;
import com.damian.wnukowski.projectboard.util.BindingResultErrorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashSet;

@RestController
@RequestMapping("/api/projectGroup")
public class ProjectGroupController {
    private BindingResultErrorMapper bindingResultErrorMapper;
    private ProjectGroupService projectGroupService;

    public ProjectGroupController(BindingResultErrorMapper bindingResultErrorMapper,
                                  ProjectGroupService projectGroupService){
        this.bindingResultErrorMapper = bindingResultErrorMapper;
        this.projectGroupService = projectGroupService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addProjectGroup(@RequestBody @Valid ProjectGroup projectGroup, BindingResult result){
        ResponseEntity<?> res = bindingResultErrorMapper.mapBindingResults(result);
        if(res != null) return res;

        projectGroupService.addProjectGroup(projectGroup);
        return new ResponseEntity<>(projectGroup, HttpStatus.CREATED);
    }

    @PostMapping("/{projectGroupName}")
    public ResponseEntity<?> addMember(@PathVariable String projectGroupName, @RequestParam String username){

        if(projectGroupService.addMember(projectGroupName, username)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getMyProjectGroups(){
        HashSet<ProjectGroup> projectGroups = projectGroupService.getProjectGroups();
        return new ResponseEntity<>(projectGroups, HttpStatus.OK);
    }

    @GetMapping("/{projectGroupName}")
    public ResponseEntity<?> getProjectGroup(@PathVariable  String projectGroupName){
        ProjectGroup res = projectGroupService.getProjectGroup(projectGroupName);
        if(res == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{projectGroupName}")
    public ResponseEntity<?> deleteProjectGroup(@PathVariable String projectGroupName){
        projectGroupService.deleteProjectGroupByProjectGroupName(projectGroupName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
