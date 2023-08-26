package com.netcracker.hackathon.smartwfm.admin.controller;

import com.netcracker.hackathon.smartwfm.admin.service.CalendarRequest;
import com.netcracker.hackathon.smartwfm.admin.service.MailCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SendMailController {

    @Autowired
    private MailCalendarService mailCalendarService;

    @Value("${mail.from}")
    private String fromEmail;

    private String link = "https://bass.netcracker.com/display/PHack21/Shortlisted+Teams+-+Hackathon+Ideas";

    @GetMapping("/sendInvite")
    public ResponseEntity<String> sendCalenderInvite() {
        try {
            mailCalendarService.sendCalendarInvite(
                    fromEmail,
                    new CalendarRequest.Builder()
                            .withSubject("Sandip Magar - Shaw Project discussion.")
                            .withBody("Hello Sandip," +
                                    "We invite you to conduct By Teams interview with the candidate: " +
                                    " " + link)
                            .withToEmail("sandip.magar@netcracker.com")
                            .withMeetingStartTime(LocalDateTime.now())
                            .withMeetingEndTime(LocalDateTime.now().plusHours(1))
                            .build()
            );
            return new ResponseEntity<>("Calender Invite Sent", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to send Calender Invite", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/sendEmail")
    public ResponseEntity<String> sendSimpleEmail() {
        try {
            mailCalendarService.sendSimpleEmail(
                    new CalendarRequest.Builder()
                            .withSubject("Simple Test Email")
                            .withBody("This Is test Email")
                            .withFromEmail(fromEmail)
                            .withToEmail("sandip.magar@netcracker.com")
                            .build()
            );
            return new ResponseEntity("Email Successfully Sent", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity("Failed to send Simple Email", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
