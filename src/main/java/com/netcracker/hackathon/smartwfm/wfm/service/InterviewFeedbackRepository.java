package com.netcracker.hackathon.smartwfm.wfm.service;

import com.netcracker.hackathon.smartwfm.wfm.dao.InterviewFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewFeedbackRepository extends JpaRepository<InterviewFeedback, String> {
    InterviewFeedback findInterviewFeedbackById(String id);
}
