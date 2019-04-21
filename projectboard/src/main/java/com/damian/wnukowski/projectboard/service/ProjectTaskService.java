package com.damian.wnukowski.projectboard.service;

import com.damian.wnukowski.projectboard.entity.ProjectTask;
import com.damian.wnukowski.projectboard.repository.ProjectTaskRepository;
import com.damian.wnukowski.projectboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class ProjectTaskService {

    private final ProjectTaskRepository projectTaskRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectTaskService(ProjectTaskRepository projectTaskRepository, UserRepository userRepository) {
        this.projectTaskRepository = projectTaskRepository;
        this.userRepository = userRepository;
    }

    public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask){

        if(projectTask.getStatus()==null || projectTask.getStatus().equals("")){
            projectTask.setStatus("TO_DO");
        }

        projectTask.setOwner(getUser());

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findAllProjectTasks(){
        com.damian.wnukowski.projectboard.entity.User owner = getUser();
        return projectTaskRepository.findAllByOwner(owner);
    }

    public ProjectTask findById(Long id){
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
