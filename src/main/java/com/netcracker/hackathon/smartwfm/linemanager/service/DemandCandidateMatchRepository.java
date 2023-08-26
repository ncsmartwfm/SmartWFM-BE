package com.netcracker.hackathon.smartwfm.linemanager.service;

import com.netcracker.hackathon.smartwfm.linemanager.dao.DemandCandidateMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandCandidateMatchRepository extends JpaRepository<DemandCandidateMatch, String> {

    public List<DemandCandidateMatch> findByCandidateId(String candidateId);
    public DemandCandidateMatch findByCandidateIdAndDemandId(String candidateId, String demandId);
    public List<DemandCandidateMatch> findByDemandId(String demandId);
}
