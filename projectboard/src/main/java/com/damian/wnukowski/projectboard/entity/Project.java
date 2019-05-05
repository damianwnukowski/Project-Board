package com.damian.wnukowski.projectboard.entity;


import com.damian.wnukowski.projectboard.util.StringListConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    public enum Status{
        TO_DO, ACTIVE, TO_ACCEPT, ACCEPTED, CANCELLED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Summary cannot be blank")
    private String summary;

    @Convert(converter = StringListConverter.class)
    private List<String> acceptanceCriterias;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    private List<StatusHistory> statusHistory = new ArrayList<>();

    @OneToMany(mappedBy = "projects")
    private ProjectGroup projectGroup;


    public Project(){}

    public Project(String summary, List<String> acceptanceCriterias, Status status){
        this.summary = summary;
        this.acceptanceCriterias = acceptanceCriterias;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getAcceptanceCriterias() {
        return acceptanceCriterias;
    }

    public void setAcceptanceCriterias(List<String> acceptanceCriteria) {
        this.acceptanceCriterias = acceptanceCriteria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<StatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<StatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public ProjectGroup getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(ProjectGroup projectGroup) {
        this.projectGroup = projectGroup;
    }
}
