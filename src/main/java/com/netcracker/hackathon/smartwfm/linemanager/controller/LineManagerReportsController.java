package com.netcracker.hackathon.smartwfm.linemanager.controller;

import com.netcracker.hackathon.smartwfm.linemanager.dao.Candidate;
import com.netcracker.hackathon.smartwfm.linemanager.dao.Demand;
import com.netcracker.hackathon.smartwfm.linemanager.dao.DemandCandidateMatch;
import com.netcracker.hackathon.smartwfm.linemanager.dao.LifeCycleStatus;
import com.netcracker.hackathon.smartwfm.linemanager.exception.CandidateNotFoundException;
import com.netcracker.hackathon.smartwfm.linemanager.service.CandidateDaoService;
import com.netcracker.hackathon.smartwfm.linemanager.service.DemandCandidateMatchDaoService;
import com.netcracker.hackathon.smartwfm.linemanager.service.DemandDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LineManagerReportsController {
    @Autowired
    private DemandDaoService demandDaoService;
    @Autowired
    private CandidateDaoService candidateDaoService;
    @Autowired
    private DemandCandidateMatchDaoService demandCandidateMatchDaoService;

    @GetMapping("/demands")
    public List<Demand> getOpenDemands() {
        return demandDaoService.getAllDemands();
    }

    @GetMapping("/candidates")
    public List<Candidate> getAvailableResources() {
        return candidateDaoService.getAvailableCandidates();
    }

    @GetMapping("/candidates/{Id}")
    public Candidate getCandidateBydId(@PathVariable String Id) {
        Candidate candidate = candidateDaoService.getCandidateById(Id);
        if (candidate == null) {
            throw new CandidateNotFoundException("Candidate with Id: " + Id + " is not found");
        }
        return candidate;
    }

    @GetMapping("candidates/statuses")
    public List<String> listOfLifeCycleStatus() {
        return Arrays.stream(LifeCycleStatus.class.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .map(field -> {
                    try {
                        return (String) field.get(LifeCycleStatus.class);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/candidates/{lineManagerEmailId}")
    public List<Candidate> getCandidateByLineManagerEmail(@PathVariable String emailId) {
        return candidateDaoService.getCandidatesByLineManagerEmailId(emailId);
    }

    @GetMapping("/candidates/{emailId}")
    public Candidate getCandidateByEmailId(@PathVariable String emailId) {
        return candidateDaoService.getCandidateByEmailId(emailId);
    }

    @PostMapping("/demands")
    public ResponseEntity<Demand> createDemand(@Validated @RequestBody Demand demand) {
        demandDaoService.save(demand);
        return new ResponseEntity<>(demand, HttpStatus.CREATED);
    }

    @PostMapping("/candidates")
    public ResponseEntity<Candidate> createCandidate(@Validated @RequestBody Candidate candidate) {
        candidateDaoService.save(candidate);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }

    @PutMapping("/candidates")
    public ResponseEntity<Candidate> updateCandidate(@Validated @RequestBody Candidate candidate) {
        candidateDaoService.save(candidate);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }

    @DeleteMapping("/demands/{Id}")
    public ResponseEntity<Object> deleteDemandById(@PathVariable String Id) {
        Demand demandById = demandDaoService.getDemandById(Id);
        if (demandById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        demandDaoService.deleteDemandById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/candidates/{Id}")
    public ResponseEntity<Object> deleteCandidateById(@PathVariable String Id) {
        if (null == candidateDaoService.getCandidateById(Id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        candidateDaoService.deleteCandidateById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/candidates/match/{candidateId}")
    public List<DemandCandidateMatch> getMatchedDemandWithCandidateId(@PathVariable String candidateId) {
        return candidateDaoService.getMatchedDemandsForCandidateById(candidateId);
    }

    @GetMapping("/demands/match/{demandId}")
    public List<DemandCandidateMatch> getMatchedCandidatesWithDemandId(@PathVariable String demandId) {
        return demandDaoService.getMatchedCandidatesForDemandById(demandId);
    }

    @PutMapping("candidates/match")
    public List<DemandCandidateMatch> updateLineManagerRecommendation(@RequestBody DemandCandidateMatch demandCandidateMatch) {
        List<DemandCandidateMatch> demandCandidateMatchesWithLMRecommendationFlag = candidateDaoService
                .getMatchedDemandsForCandidateById(demandCandidateMatch.getCandidateId());
        demandCandidateMatchesWithLMRecommendationFlag
                .stream()
                .filter(e -> e.getDemandId().equalsIgnoreCase(demandCandidateMatch.getDemandId()))
                .forEach(e -> e.setRecommendation(true));
        demandCandidateMatchesWithLMRecommendationFlag.stream()
                .forEach(e -> demandCandidateMatchDaoService.saveDemandCandidateMatchRecord(e));
        return demandCandidateMatchesWithLMRecommendationFlag;
    }

    @PutMapping("candidate/sendForDOApproval")
    public List<DemandCandidateMatch> sendForDOApproval(@RequestBody DemandCandidateMatch demandCandidateMatch) {
        DemandCandidateMatch updatedObj = demandCandidateMatchDaoService.findByCandidateIdAndDemandId(
                demandCandidateMatch.getCandidateId(), demandCandidateMatch.getDemandId());
        updatedObj.setLifecycleStatus(LifeCycleStatus.WAITING_FOR_DO_APPROVAL);
        demandCandidateMatchDaoService.saveDemandCandidateMatchRecord(updatedObj);
        return demandCandidateMatchDaoService.findByCandidateId(demandCandidateMatch.getCandidateId());
    }

    @PutMapping("candidate/approvedbyDO")
    public List<DemandCandidateMatch> approvedByDO(@RequestBody DemandCandidateMatch demandCandidateMatch) {
        DemandCandidateMatch updatedObj = demandCandidateMatchDaoService.findByCandidateIdAndDemandId(
                demandCandidateMatch.getCandidateId(), demandCandidateMatch.getDemandId());
        updatedObj.setLifecycleStatus(LifeCycleStatus.APPROVED_BY_DO);
        demandCandidateMatchDaoService.saveDemandCandidateMatchRecord(updatedObj);
        return demandCandidateMatchDaoService.findByCandidateId(demandCandidateMatch.getCandidateId());
    }
}
