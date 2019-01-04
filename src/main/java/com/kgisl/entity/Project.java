package com.kgisl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Project
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectid;
    private String projectname;
    private Long teamid;

    /**
     * @return the projectid
     */
    public Long getProjectid() {
        return projectid;
    }
    /**
     * @param projectid the projectid to set
     */
    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }
    /**
     * @return the projectname
     */
    public String getProjectname() {
        return projectname;
    }
    /**
     * @param projectname the projectname to set
     */
    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
    /**
     * @return the teamid
     */
    public Long getTeamid() {
        return teamid;
    }
    /**
     * @param teamid the teamid to set
     */
    public void setTeamid(Long teamid) {
        this.teamid = teamid;
    }
}