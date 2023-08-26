package com.netcracker.hackathon.smartwfm.linemanager.controller;

import com.netcracker.hackathon.smartwfm.linemanager.dao.Candidate;
import com.netcracker.hackathon.smartwfm.linemanager.dao.Demand;
import com.netcracker.hackathon.smartwfm.linemanager.dao.DemandCandidateMatch;
import com.netcracker.hackathon.smartwfm.linemanager.dao.LifecycleStatus;
import com.netcracker.hackathon.smartwfm.linemanager.exception.CandidateNotFoundException;
import com.netcracker.hackathon.smartwfm.linemanager.service.CandidateDaoService;
import com.netcracker.hackathon.smartwfm.linemanager.service.DemandCandidateMatchDaoService;
import com.netcracker.hackathon.smartwfm.linemanager.service.DemandDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<LifecycleStatus> listOfLifeCycleStatus() {
        return List.of(LifecycleStatus.values());
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

    @PutMapping("candidates/match")
    public ResponseEntity<DemandCandidateMatch> updateLineManagerRecommendation(@RequestBody DemandCandidateMatch demandCandidateMatch) {
        DemandCandidateMatch demandCandidateMatchToUpdate = demandCandidateMatchDaoService.findByCandidateIdAndDemandId(demandCandidateMatch.getCandidateId(), demandCandidateMatch.getDemandId());
        demandCandidateMatchToUpdate.setLineManagerRecommendation(demandCandidateMatchToUpdate.isLineManagerRecommendation() ? false : true);
        demandCandidateMatchDaoService.saveDemandCandidateMatchRecord(demandCandidateMatchToUpdate);
        return new ResponseEntity<>(demandCandidateMatch, HttpStatus.OK);
    }

}
