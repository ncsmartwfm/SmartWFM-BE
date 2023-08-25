package com.netcracker.hackathon.smartwfm.wfm.controller;

import com.netcracker.hackathon.smartwfm.wfm.dao.InterviewFeedback;
import com.netcracker.hackathon.smartwfm.wfm.exceptions.InterviewFeedbackNotFoundException;
import com.netcracker.hackathon.smartwfm.wfm.service.InterviewFeedbackDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InterviewFeedbackController {
    @Autowired
    private InterviewFeedbackDaoService interviewFeedbackDaoService;

    @PostMapping("/interviewFeedbacks")
    public void addInterviewFeedback(@Validated @RequestBody InterviewFeedback interviewFeedback) {
        interviewFeedbackDaoService.save(interviewFeedback);
    }
    @GetMapping("/interviewFeedbacks")
    public List<InterviewFeedback> getInterviewFeedbacks() {
        return interviewFeedbackDaoService.getInterviewFeedbacks();
    }

    @GetMapping("/interviewFeedbacks/{Id}")
    public InterviewFeedback getInterviewFeedbackById(@PathVariable String Id) {
        InterviewFeedback interviewFeedback = interviewFeedbackDaoService.getInterviewFeedbackById(Id);
        if(interviewFeedback == null) {
            throw new InterviewFeedbackNotFoundException("InterviewFeedback with Id: "+Id+" is not found");
        }
        return interviewFeedback;
    }

}
