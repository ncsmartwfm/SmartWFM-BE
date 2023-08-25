package com.netcracker.hackathon.smartwfm.wfm.service;


import com.netcracker.hackathon.smartwfm.wfm.dao.InterviewFeedback;
import com.netcracker.hackathon.smartwfm.wfm.exceptions.InterviewFeedbackNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewFeedbackDaoService {


    @Autowired
    private InterviewFeedbackRepository interviewFeedbackRepository;

    public void save(InterviewFeedback interviewFeedback) {
        interviewFeedbackRepository.save(interviewFeedback);
    }

    public List<InterviewFeedback> getInterviewFeedbacks() {
        return interviewFeedbackRepository.findAll();
    }

    public InterviewFeedback getInterviewFeedbackById(String Id) {
        InterviewFeedback interviewFeedback = interviewFeedbackRepository.findInterviewFeedbackById(Id);
        if (interviewFeedback != null) {
            return interviewFeedback;
        }
        throw new InterviewFeedbackNotFoundException("Interview Feedback with Id: " + Id + " does not exist");
    }

}
