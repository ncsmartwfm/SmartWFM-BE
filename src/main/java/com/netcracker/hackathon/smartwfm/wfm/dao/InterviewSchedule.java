package com.netcracker.hackathon.smartwfm.wfm.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class InterviewSchedule {
    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

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
    private String decision;

    public InterviewSchedule() {
    }

}
