package com.netcracker.hackathon.smartwfm.linemanager.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@JsonPropertyOrder({"candidateId", "firstName", "lastName", "domain",
        "skillSet", "yearsOfExperience", "yearOfExperienceInCurrentDomain",
        "candidateEmailId", "lineManagerOfficialId",
        "lineManagerName", "lineManagerEmailId", "country", "city", "status"})
public class Candidate {
    @Id
    @JsonProperty("candidateId")
    private String candidateId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("skillSet")
    private List<String> skillSet;
    @JsonProperty("yearsOfExperience")
    private Integer yearsOfExperience;
    @JsonProperty("yearOfExperienceInCurrentDomain")
    private Integer yearOfExperienceInCurrentDomain;
    @JsonProperty("candidateEmailId")
    private String candidateEmailId;
    @JsonProperty("lineManagerOfficialId")
    @NotNull(message = "Line manager Id can't be null for a candidate")
    private String lineManagerId;
    @JsonProperty("lineManagerName")
    private String lineManagerName;
    @JsonProperty("lineManagerEmailId")
    private String lineManagerEmailId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;

    public Candidate() {
    }

    public Candidate(String candidateId, String firstName,
                     String lastName, String domain, List<String> skillSet,
                     Integer yearsOfExperience, Integer yearOfExperienceInCurrentDomain,
                     String candidateEmailId, String lineManagerId,
                     String lineManagerEmailId, String country, String city,
                     String lineManagerName) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.domain = domain;
        this.skillSet = skillSet;
        this.yearsOfExperience = yearsOfExperience;
        this.yearOfExperienceInCurrentDomain = yearOfExperienceInCurrentDomain;
        this.candidateEmailId = candidateEmailId;
        this.lineManagerId = lineManagerId;
        this.lineManagerEmailId = lineManagerEmailId;
        this.country = country;
        this.city = city;
        this.lineManagerName = lineManagerName;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getLineManagerId() {
        return lineManagerId;
    }

    public void setLineManagerId(String lineManagerId) {
        this.lineManagerId = lineManagerId;
    }

    public String getLineManagerName() {
        return lineManagerName;
    }

    public void setLineManagerName(String lineManagerName) {
        this.lineManagerName = lineManagerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<String> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(List<String> skillSet) {
        this.skillSet = skillSet;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Integer getYearOfExperienceInCurrentDomain() {
        return yearOfExperienceInCurrentDomain;
    }

    public void setYearOfExperienceInCurrentDomain(Integer yearOfExperienceInCurrentDomain) {
        this.yearOfExperienceInCurrentDomain = yearOfExperienceInCurrentDomain;
    }

    public String getCandidateEmailId() {
        return candidateEmailId;
    }

    public void setCandidateEmailId(String candidateEmailId) {
        this.candidateEmailId = candidateEmailId;
    }

    public String getLineManagerEmailId() {
        return lineManagerEmailId;
    }

    public void setLineManagerEmailId(String lineManagerEmailId) {
        this.lineManagerEmailId = lineManagerEmailId;
    }
}
