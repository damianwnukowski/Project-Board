package com.damian.wnukowski.projectboard.repository;


import com.damian.wnukowski.projectboard.entity.ProjectGroup;
import com.damian.wnukowski.projectboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long> {
    ProjectGroup findByProjectGroupNameAndOwnerOrMembersContains(String projectGroupName, User owner, User member);
    ProjectGroup findByProjectGroupNameAndOwner(String projectGroupName, User owner);
    HashSet<ProjectGroup> findAllByOwnerOrMembersContains(User owner, User member);
    void deleteByProjectGroupNameAndOwner(String projectGroupName, User owner);
}
