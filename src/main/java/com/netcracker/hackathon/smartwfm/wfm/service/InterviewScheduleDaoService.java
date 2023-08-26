package com.netcracker.hackathon.smartwfm.wfm.service;


import com.netcracker.hackathon.smartwfm.wfm.dao.InterviewSchedule;
import com.netcracker.hackathon.smartwfm.wfm.exceptions.InterviewScheduleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewScheduleDaoService {


    @Autowired
    private InterviewScheduleRepository interviewScheduleRepository;

    public void save(InterviewSchedule interviewSchedule) {
        interviewScheduleRepository.save(interviewSchedule);
    }

    public List<InterviewSchedule> getInterviewSchedules() {
        return interviewScheduleRepository.findAll();
    }

    public InterviewSchedule getInterviewScheduleById(String Id) {
        InterviewSchedule interviewSchedule = interviewScheduleRepository.findInterviewScheduleById(Id);
        if (interviewSchedule != null) {
            return interviewSchedule;
        }
        throw new InterviewScheduleNotFoundException("Interview Schedule with Id: " + Id + " does not exist");
    }

}
