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
    @JsonProperty("interviewFeedbackId")
    private String demandId;
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
