package com.netcracker.hackathon.smartwfm.wfm.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class InterviewFeedback {
    @Id
    @UuidGenerator
    private String Id;
     public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    @JsonProperty("candidateId")
    private String candidateId;
    @JsonProperty("panelId")
    private String panelId;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @JsonProperty("decision")
    private double matchPercentage;

    public InterviewFeedback() {
    }

}
