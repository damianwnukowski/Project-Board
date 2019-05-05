package com.damian.wnukowski.projectboard.service;

import com.damian.wnukowski.projectboard.entity.Project;
import com.damian.wnukowski.projectboard.entity.User;
import com.damian.wnukowski.projectboard.repository.ProjectTaskRepository;
import com.damian.wnukowski.projectboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    private final ProjectTaskRepository projectTaskRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectTaskRepository projectTaskRepository, UserRepository userRepository) {
        this.projectTaskRepository = projectTaskRepository;
        this.userRepository = userRepository;
    }

    public Project saveOrUpdateProjectTask(Project project){

        if(project.getStatus()==null || project.getStatus().equals("")){
            project.setStatus(Project.Status.TO_DO);
        }

        

        return projectTaskRepository.save(project);
    }

    public Iterable<Project> findAllProjectTasks(){
        com.damian.wnukowski.projectboard.entity.User owner = getUser();
        return projectTaskRepository.findAllByOwner(owner);
    }

    public Project findById(Long id){
        com.damian.wnukowski.projectboard.entity.User owner = getUser();
        return projectTaskRepository.findByIdAndOwner(id, owner);

    }

    public void delete(Long id){
        com.damian.wnukowski.projectboard.entity.User owner = getUser();
        projectTaskRepository.deleteByIdAndOwner(id, owner);
    }

    private com.damian.wnukowski.projectboard.entity.User getUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByUsername(username);
    }
}
