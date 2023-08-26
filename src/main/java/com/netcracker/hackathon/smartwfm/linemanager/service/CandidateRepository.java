package com.netcracker.hackathon.smartwfm.linemanager.service;

import com.netcracker.hackathon.smartwfm.linemanager.dao.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, String> {
    Candidate findByCandidateId(String candidateId);
    Candidate findByCandidateEmailId(String candidateEmailId);
    List<Candidate> findByLineManagerEmailId(String lineManagerEmailId);
}
