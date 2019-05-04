package com.damian.wnukowski.projectboard.entity;

import com.sun.org.glassfish.gmbal.ManagedAttribute;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @Size(min = 4, max = 20)
    private String username;
    @Size(min = 4, max = 72)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    @ManyToMany(mappedBy = "members")
    private List<ProjectGroup> projectGroups;

    @OneToMany(mappedBy = "owner")
    private List<ProjectGroup> ownedProjectGroups;

    User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
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

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

    public void setProjectGroups(List<ProjectGroup> projectGroups){
        this.projectGroups = projectGroups;
    }

    public List<ProjectGroup> getProjectGroups(){
        return projectGroups;
    }

    public List<ProjectGroup> getOwnedProjectGroups() {
        return ownedProjectGroups;
    }

    public void setOwnedProjectGroups(List<ProjectGroup> ownedProjectGroups) {
        this.ownedProjectGroups = ownedProjectGroups;
    }
}
