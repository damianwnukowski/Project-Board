package com.damian.wnukowski.projectboard.repository;

import com.damian.wnukowski.projectboard.entity.ProjectTask;
import com.damian.wnukowski.projectboard.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
    ProjectTask findByIdAndOwner(Long id, User owner);
    List<ProjectTask> findAllByOwner(User owner);
    void deleteByIdAndOwner(Long id, User owner);
}
