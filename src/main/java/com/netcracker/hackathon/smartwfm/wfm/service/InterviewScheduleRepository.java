package com.netcracker.hackathon.smartwfm.wfm.service;

import com.netcracker.hackathon.smartwfm.wfm.dao.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, String> {
    InterviewSchedule findInterviewScheduleById(String id);
}
