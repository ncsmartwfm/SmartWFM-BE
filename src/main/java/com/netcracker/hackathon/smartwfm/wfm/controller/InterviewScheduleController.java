package com.netcracker.hackathon.smartwfm.wfm.controller;

import com.netcracker.hackathon.smartwfm.wfm.dao.InterviewSchedule;
import com.netcracker.hackathon.smartwfm.wfm.exceptions.InterviewScheduleNotFoundException;
import com.netcracker.hackathon.smartwfm.wfm.service.InterviewScheduleDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InterviewScheduleController {
    @Autowired
    private InterviewScheduleDaoService interviewScheduleDaoService;

    @PostMapping("/interviewSchedules")
    public void addInterviewSchedule(@Validated @RequestBody InterviewSchedule interviewSchedule) {
        interviewScheduleDaoService.save(interviewSchedule);
    }
    @GetMapping("/interviewSchedules")
    public List<InterviewSchedule> getInterviewSchedules() {
        return interviewScheduleDaoService.getInterviewSchedules();
    }

    @GetMapping("/interviewSchedules/{Id}")
    public InterviewSchedule getInterviewScheduleById(@PathVariable String Id) {
        InterviewSchedule interviewSchedule = interviewScheduleDaoService.getInterviewScheduleById(Id);
        if(interviewSchedule == null) {
            throw new InterviewScheduleNotFoundException("InterviewSchedule with Id: "+Id+" is not found");
        }
        return interviewSchedule;
    }

}
