package com.damian.wnukowski.projectboard.repository;

import com.damian.wnukowski.projectboard.entity.Project;
import com.damian.wnukowski.projectboard.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectTaskRepository extends CrudRepository<Project, Long> {
    Project findByIdAndOwner(Long id, User owner);
    List<Project> findAllByOwner(User owner);
    void deleteByIdAndOwner(Long id, User owner);
}
