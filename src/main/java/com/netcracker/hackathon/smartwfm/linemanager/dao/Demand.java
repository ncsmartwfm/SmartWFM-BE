package com.netcracker.hackathon.smartwfm.linemanager.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@JsonPropertyOrder({"demandId", "projectName", "projectRole", "desiredSkillSet",
        "desiredYearsOfExperience", "domain", "location", "desiredLocation"})
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
    @JsonProperty("location")
    private String location;
    @JsonProperty("desiredLocation")
    private String desiredLocation;

    public Demand() {
        // forJAXB serialization purpose
    }

    public Demand(String demandId, String projectName, String projectRole,
                  List<String> desiredSkillSet, Integer desiredYearsOfExperience,
                  String domain, String location, String desiredLocation) {
        this.demandId = demandId;
        this.projectName = projectName;
        this.projectRole = projectRole;
        this.desiredSkillSet = desiredSkillSet;
        this.desiredYearsOfExperience = desiredYearsOfExperience;
        this.domain = domain;
        this.location = location;
        this.desiredLocation = desiredLocation;
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

    public String getDesiredLocation() {
        return desiredLocation;
    }

    public void setDesiredLocation(String desiredLocation) {
        this.desiredLocation = desiredLocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
