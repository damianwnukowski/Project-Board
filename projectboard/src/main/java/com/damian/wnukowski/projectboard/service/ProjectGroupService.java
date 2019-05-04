package com.damian.wnukowski.projectboard.service;

import com.damian.wnukowski.projectboard.entity.ProjectGroup;
import com.damian.wnukowski.projectboard.repository.ProjectGroupRepository;
import com.damian.wnukowski.projectboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProjectGroupService {
    private UserRepository userRepository;
    private ProjectGroupRepository projectGroupRepository;

    @Autowired
    public ProjectGroupService(UserRepository userRepository, ProjectGroupRepository projectGroupRepository){
        this.userRepository = userRepository;
        this.projectGroupRepository = projectGroupRepository;
    }

    public void addProjectGroup(ProjectGroup projectGroup) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        projectGroup.setOwner(userRepository.findUserByUsername(userName));
        projectGroupRepository.save(projectGroup);
    }
}
