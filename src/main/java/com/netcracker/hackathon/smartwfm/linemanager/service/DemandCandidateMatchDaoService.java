package com.netcracker.hackathon.smartwfm.linemanager.service;

import com.netcracker.hackathon.smartwfm.linemanager.dao.DemandCandidateMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandCandidateMatchDaoService {
    @Autowired
    private DemandCandidateMatchRepository demandCandidateMatchRepository;

    public void saveDemandCandidateMatchRecord(DemandCandidateMatch demandCandidateMatch) {
        demandCandidateMatchRepository.save(demandCandidateMatch);
    }

    public DemandCandidateMatch findByCandidateIdAndDemandId(String candidateId, String demandId) {
        return demandCandidateMatchRepository.findByCandidateIdAndDemandId(candidateId, demandId);
    }

    public List<DemandCandidateMatch> findListOfDemandCandidateMatchByCandidateOfficialId(String candidateId) {
     return demandCandidateMatchRepository.findByCandidateId(candidateId);
    }

    public List<DemandCandidateMatch> findListOfDemandCandidateMatchByDemandId(String demandId) {
        return demandCandidateMatchRepository.findByDemandId(demandId);
    }
}
