package com.damian.wnukowski.projectboard.service;

import com.damian.wnukowski.projectboard.entity.ProjectGroup;
import com.damian.wnukowski.projectboard.entity.User;
import com.damian.wnukowski.projectboard.repository.ProjectGroupRepository;
import com.damian.wnukowski.projectboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;


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

    public boolean addMember(String projectGroupName, String username) {
        User owner = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        ProjectGroup projectGroup = projectGroupRepository.findByProjectGroupNameAndOwner(projectGroupName, owner);
        if(projectGroup == null)
            return false;

        User user = userRepository.findUserByUsername(username);
        if(user == null)
            return false;

        projectGroup.getMembers().add(user);
        projectGroupRepository.save(projectGroup);
        return true;

    }

    public HashSet<ProjectGroup> getProjectGroups() {
        User user = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return projectGroupRepository.findAllByOwnerOrMembersContains(user,user);
    }

    public ProjectGroup getProjectGroup(String projectGroupName) {
        User user = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return projectGroupRepository.findByProjectGroupNameAndOwnerOrMembersContains(projectGroupName, user, user);
    }

    public void deleteProjectGroupByProjectGroupName(String projectGroupName){
        User owner = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        projectGroupRepository.deleteByProjectGroupNameAndOwner(projectGroupName, owner);
    }
}
