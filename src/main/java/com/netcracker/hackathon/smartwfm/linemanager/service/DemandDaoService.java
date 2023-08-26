package com.netcracker.hackathon.smartwfm.linemanager.service;

import com.netcracker.hackathon.smartwfm.linemanager.dao.Candidate;
import com.netcracker.hackathon.smartwfm.linemanager.dao.Demand;
import com.netcracker.hackathon.smartwfm.linemanager.dao.DemandCandidateMatch;
import com.netcracker.hackathon.smartwfm.linemanager.exception.DemandNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandDaoService {
    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ProfileMatcherService profileMatcherService;

    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    public void save(Demand demand) {
        demandRepository.save(demand);
    }

    public Demand getDemandById(String Id) {
        Demand demand = demandRepository.findByDemandId(Id);
        if (demand != null) {
            return demand;
        }
        throw new DemandNotFoundException("Demand with Id " + Id + " does not exist");
    }

    public void deleteDemandById(String Id) {
        Demand demand = demandRepository.findByDemandId(Id);
        if (demand != null) {
            demandRepository.delete(demand);
        }
        throw new DemandNotFoundException("Demand for the Id " + Id + " does not exist");
    }

    public List<DemandCandidateMatch> getMatchedCandidatesForDemandById(String demandId) {
        Demand demand = demandRepository.findByDemandId(demandId);
        List<Candidate> candidates = candidateRepository.findAll();
        if (demand == null) {
            throw new DemandNotFoundException("Candidate with Id " + demandId + " does not exist");
        }
        List<DemandCandidateMatch> demandCandidateMatches = new ArrayList<>();
        for (Candidate candidate : candidates) {
            demandCandidateMatches.add(profileMatcherService.calculateDemandCandidateMatchingPercentage(candidate, demand));
        }
        return demandCandidateMatches;
    }
}
