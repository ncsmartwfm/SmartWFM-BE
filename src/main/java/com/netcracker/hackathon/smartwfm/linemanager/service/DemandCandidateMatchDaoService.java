package com.netcracker.hackathon.smartwfm.linemanager.service;

import com.netcracker.hackathon.smartwfm.admin.service.MailCalendarService;
import com.netcracker.hackathon.smartwfm.linemanager.dao.DemandCandidateMatch;
import com.netcracker.hackathon.smartwfm.linemanager.dao.LifeCycleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandCandidateMatchDaoService {

    private static final String SMARTWFM_DO_APPROVAL_SENT = "Domain Owner Approval Needed For Demand";

    @Value("${mail.from}")
    private String fromEmail;

    @Autowired
    private MailCalendarService mailCalendarService;

    @Autowired
    private DemandCandidateMatchRepository demandCandidateMatchRepository;

    public void saveDemandCandidateMatchRecord(DemandCandidateMatch demandCandidateMatch) {
        if (LifeCycleStatus.WAITING_FOR_DO_APPROVAL.equals(demandCandidateMatch.getLifecycleStatus()))
        /*try {
            String body = "Hello Sandip, Please provide your approval on SMARTWFM for Demand to schedule interview ";
            mailCalendarService.sendMailNotification(SMARTWFM_DO_APPROVAL_SENT, body, fromEmail, "manvendrav@gmail.com");
        } catch (Exception exception) {
            throw exception;
        }*/
        demandCandidateMatchRepository.save(demandCandidateMatch);
    }

    public DemandCandidateMatch findByCandidateIdAndDemandId(String candidateId, String demandId) {
        return demandCandidateMatchRepository.findByCandidateIdAndDemandId(candidateId, demandId);
    }

    public List<DemandCandidateMatch> findByCandidateId(String candidateId) {
     return demandCandidateMatchRepository.findByCandidateId(candidateId);
    }

    public List<DemandCandidateMatch> findListOfDemandCandidateMatchByDemandId(String demandId) {
        return demandCandidateMatchRepository.findByDemandId(demandId);
    }
}
