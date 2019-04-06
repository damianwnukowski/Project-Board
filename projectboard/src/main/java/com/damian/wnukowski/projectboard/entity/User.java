package com.damian.wnukowski.projectboard.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL) //If user doesn't exist we don't need his abandoned projects
    private List<ProjectTask> projectTasks;

    public User(String username, String password, List<ProjectTask> projectTasks) {
        this.username = username;
        this.password = password;
        this.projectTasks = projectTasks;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ProjectTask> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<ProjectTask> projectTasks) {
        this.projectTasks = projectTasks;
    }
}
