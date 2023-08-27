package com.netcracker.hackathon.smartwfm.linemanager.dao;

import java.io.Serializable;

public class DemandCandidateMatchId implements Serializable {

    private String candidateId;
    private String demandId;

    public DemandCandidateMatchId() {
    }

    public DemandCandidateMatchId(String candidateId, String demandId) {
        this.candidateId = candidateId;
        this.demandId = demandId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }
}
