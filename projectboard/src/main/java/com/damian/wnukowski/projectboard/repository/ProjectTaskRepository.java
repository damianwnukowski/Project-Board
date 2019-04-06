package com.damian.wnukowski.projectboard.repository;

import com.damian.wnukowski.projectboard.entities.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
    ProjectTask getById(Long id);
}
