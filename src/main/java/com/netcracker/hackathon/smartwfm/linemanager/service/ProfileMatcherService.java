package com.netcracker.hackathon.smartwfm.linemanager.service;

import com.netcracker.hackathon.smartwfm.linemanager.dao.Candidate;
import com.netcracker.hackathon.smartwfm.linemanager.dao.Demand;
import com.netcracker.hackathon.smartwfm.linemanager.dao.DemandCandidateMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileMatcherService {
    @Autowired
    private DemandCandidateMatchRepository demandCandidateMatchRepository;

    public DemandCandidateMatch calculateMatchingPercentage(Candidate candidate, Demand demand) {
        DemandCandidateMatch demandCandidateMatch = new DemandCandidateMatch();
        List<String> candidateSkills = candidate.getSkillSet();
        int candidateExperience = candidate.getYearsOfExperience();

        List<String> requiredSkills = demand.getDesiredSkillSet();
        int requiredExperience = demand.getDesiredYearsOfExperience();
        double skillMatchPercentage = (double) countMatchingSkills(candidateSkills, requiredSkills) / requiredSkills.size() * 100;
        double experienceMatchPercentage  = 0.0;
        if(candidateExperience > requiredExperience) {
            experienceMatchPercentage = 100;
        } else {
            experienceMatchPercentage = (double) candidateExperience / requiredExperience * 100;
        }
        double overallMatchingPercentage = (skillMatchPercentage + experienceMatchPercentage) / 2;
        demandCandidateMatch.setCandidateId(candidate.getCandidateId());
        demandCandidateMatch.setDemandId(demand.getDemandId());
        demandCandidateMatch.setMatchPercentage(overallMatchingPercentage);
        demandCandidateMatchRepository.save(demandCandidateMatch);
        return demandCandidateMatch;
    }

    public int countMatchingSkills(List<String> candidateSkills, List<String> desiredSkills) {
        int count = 0;
        for (String skill : desiredSkills) {
            if (candidateSkills.contains(skill)) {
                count++;
            }
        }
        return count;
    }
}
