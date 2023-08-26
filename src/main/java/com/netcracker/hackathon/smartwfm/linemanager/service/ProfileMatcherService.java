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

    public DemandCandidateMatch calculateCandidateDemandMatchingPercentage(Candidate candidate, Demand demand) {
        DemandCandidateMatch demandCandidateMatch = new DemandCandidateMatch();
        demandCandidateMatch.setFirstName(candidate.getFirstName());
        demandCandidateMatch.setLastName(candidate.getLastName());
        demandCandidateMatch.setProjectName(demand.getProjectName());
        demandCandidateMatch.setProjectRole(demand.getProjectRole());
        demandCandidateMatch.setRecommendation(false);
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
        int overallMatchingPercentage = (int) ((skillMatchPercentage + experienceMatchPercentage) / 2);
        demandCandidateMatch.setCandidateId(candidate.getCandidateId());
        demandCandidateMatch.setDemandId(demand.getDemandId());
        demandCandidateMatch.setMatchPercentage(overallMatchingPercentage);
        return demandCandidateMatch;
    }

    public int countMatchingSkills(List<String> toBeMatchedSkills, List<String> toBeMatchedAgainstSkills) {
        int count = 0;
        for (String skill : toBeMatchedAgainstSkills) {
            if (toBeMatchedSkills.contains(skill)) {
                count++;
            }
        }
        return count;
    }
}
