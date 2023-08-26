package com.netcracker.hackathon.smartwfm.linemanager.service;

import com.netcracker.hackathon.smartwfm.linemanager.dao.Candidate;
import com.netcracker.hackathon.smartwfm.linemanager.dao.Demand;
import com.netcracker.hackathon.smartwfm.linemanager.dao.DemandCandidateMatch;
import com.netcracker.hackathon.smartwfm.linemanager.exception.CandidateNotFoundException;
import com.netcracker.hackathon.smartwfm.linemanager.exception.DemandNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateDaoService {

    @Autowired
    private ProfileMatcherService profileMatcherService;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private DemandRepository demandRepository;

    public void save(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public List<Candidate> getAvailableCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(String Id) {
        Candidate candidate = candidateRepository.findByCandidateId(Id);
        if (candidate != null) {
            return candidate;
        }
        throw new CandidateNotFoundException("Candidate with Id: " + Id + " does not exist");
    }

    public List<Candidate> getCandidatesByLineManagerEmailId(String emailId) {
        return candidateRepository.findByLineManagerEmailId(emailId);
    }

    public Candidate getCandidateByEmailId(String emailId) {
        Candidate candidate = candidateRepository.findByCandidateEmailId(emailId);
        if (candidate != null) {
            return candidate;
        }
        throw new CandidateNotFoundException("Candidate with emailId: " + emailId + " does not exist");
    }

    public void deleteCandidateById(String Id) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(Id);
        if (candidateOptional.isPresent()) {
            candidateRepository.deleteById(Id);
        }
        throw new CandidateNotFoundException("Candidate with Id " + Id + " does not exist");
    }

    public List<DemandCandidateMatch> getMatchedDemandsForCandidateById(String candidateId) {
        List<Demand> demands = demandRepository.findAll();
        Candidate candidate = getCandidateById(candidateId);
        if (candidate == null) {
            throw new CandidateNotFoundException("Candidate with Id " + candidateId + " does not exist");
        }
        List<DemandCandidateMatch> demandCandidateMatches = new ArrayList<>();
        for (Demand demand : demands) {
            demandCandidateMatches.add(profileMatcherService.calculateCandidateDemandMatchingPercentage(candidate, demand));
        }
        return demandCandidateMatches;
    }

}
