package com.netcracker.hackathon.smartwfm.linemanager.controller;

import com.netcracker.hackathon.smartwfm.linemanager.dao.Candidate;
import com.netcracker.hackathon.smartwfm.linemanager.dao.Demand;
import com.netcracker.hackathon.smartwfm.linemanager.exception.CandidateNotFoundException;
import com.netcracker.hackathon.smartwfm.linemanager.service.CandidateDaoService;
import com.netcracker.hackathon.smartwfm.linemanager.service.DemandDaoService;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(candidate == null) {
            throw new CandidateNotFoundException("Candidate with Id: "+Id+" is not found");
        }
        return candidate;
    }

    @PostMapping("/demands")
    public void createDemand(@Validated @RequestBody Demand demand) {
        demandDaoService.save(demand);
    }

    @PostMapping("/candidates")
    public ResponseEntity<Candidate> createCandidate(@Validated @RequestBody Candidate candidate) {
        candidateDaoService.save(candidate);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/demands/{Id}")
    public void deleteDemandById(@PathVariable String Id) {
        demandDaoService.deleteDemandById(Id);
    }

    @DeleteMapping("/candidates/{Id}")
    public void deleteCandidateById(@PathVariable String Id) {
        candidateDaoService.deleteCandidateById(Id);
    }

    @GetMapping("/candidates/match/{candidateId}")
    public Candidate getMatchedDemandWithCandidateId(@PathVariable String candidateId) {
        candidateDaoService.updateCandidateWithDemandCandidateMatchByCandidateId(candidateId);
        Candidate candidateBydId = getCandidateBydId(candidateId);
        return candidateBydId;
    }

    @GetMapping("/candidates/match")
    public List<Candidate> getMatchedDemandForAllCandidates() {
        candidateDaoService.updateCandidateWithDemandCandidateMatchForAllCandidates();
        return candidateDaoService.getAvailableCandidates();
    }
}
