package com.netcracker.hackathon.smartwfm.linemanager.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@JsonPropertyOrder({"demandId", "projectName", "projectRole", "desiredSkillSet",
        "desiredYearsOfExperience", "domain", "location", "desiredLocations"})
public class Demand {
    @Id
    @JsonProperty("demandId")
    private String demandId;
    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("projectRole")
    private String projectRole;
    @JsonProperty("desiredSkillSet")
    private List<String> desiredSkillSet;
    @JsonProperty("desiredYearsOfExperience")
    private Integer desiredYearsOfExperience;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("desiredLocations")
    private List<String> desiredLocations;

    public Demand() {
        // forJAXB serialization purpose
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public List<String> getDesiredLocations() {
        return desiredLocations;
    }

    public void setDesiredLocations(List<String> desiredLocations) {
        this.desiredLocations = desiredLocations;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getDesiredSkillSet() {
        return desiredSkillSet;
    }

    public void setDesiredSkillSet(List<String> desiredSkillSet) {
        this.desiredSkillSet = desiredSkillSet;
    }

    public Integer getDesiredYearsOfExperience() {
        return desiredYearsOfExperience;
    }

    public void setDesiredYearsOfExperience(Integer desiredYearsOfExperience) {
        this.desiredYearsOfExperience = desiredYearsOfExperience;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
