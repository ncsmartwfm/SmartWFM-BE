package com.netcracker.hackathon.smartwfm.common.service;

import jakarta.activation.DataHandler;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class MailCalendarService {

    @Autowired
    private JavaMailSender mailSender;

    public MailCalendarService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail( CalendarRequest calendarRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(calendarRequest.getFromEmail());
        message.setTo(calendarRequest.getToEmail());
        message.setSubject(calendarRequest.getSubject());
        message.setText(calendarRequest.getBody());
        mailSender.send(message);
    }

    public void sendCalendarInvite(String fromEmail, CalendarRequest calendarRequest) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.addHeaderLine("method=REQUEST");
        mimeMessage.addHeaderLine("charset=UTF-8");
        mimeMessage.addHeaderLine("component=VEVENT");
        mimeMessage.setFrom(fromEmail);
        mimeMessage.addRecipients(Message.RecipientType.TO,calendarRequest.getToEmail());
        mimeMessage.setSubject(calendarRequest.getSubject());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        StringBuilder builder = new StringBuilder();
        builder.append("BEGIN:VCALENDAR\n" +
                "METHOD:REQUEST\n" +
                "PRODID:Microsoft Exchange Server 2010\n" +
                "VERSION:2.0\n" +
                "BEGIN:VTIMEZONE\n" +
                "TZID:Asia/Kolkata\n" +
                "END:VTIMEZONE\n" +
                "BEGIN:VEVENT\n" +
                "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:" + calendarRequest.getToEmail() + "\n" +
                "ORGANIZER;CN=" + fromEmail + ":MAILTO:" + fromEmail + "\n" +
                "DESCRIPTION;LANGUAGE=en-US:" + calendarRequest.getBody() + "\n" +
                "UID:" + calendarRequest.getUid()+"\n" +
                "SUMMARY;LANGUAGE=en-US:"+ calendarRequest.getSubject() + "\n" +
                "DTSTART:" + formatter.format(calendarRequest.getMeetingStartTime()).replace(" ", "T") + "\n" +
                "DTEND:" + formatter.format(calendarRequest.getMeetingEndTime()).replace(" ", "T") + "\n" +
                "CLASS:PUBLIC\n" +
                "PRIORITY:5\n" +
                "DTSTAMP:20200922T105302Z\n" +
                "TRANSP:OPAQUE\n" +
                "STATUS:CONFIRMED\n" +
                "SEQUENCE:$sequenceNumber\n" +
                "LOCATION;LANGUAGE=en-US:Microsoft Teams Meeting\n" +
                "BEGIN:VALARM\n" +
                "DESCRIPTION:REMINDER\n" +
                "TRIGGER;RELATED=START:-PT15M\n" +
                "ACTION:DISPLAY\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR");

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");
        messageBodyPart.setHeader("Content-ID", "calendar_message");
        messageBodyPart.setDataHandler(new DataHandler(
                new ByteArrayDataSource(builder.toString(), "text/calendar;method=REQUEST;name=\"invite.ics\"")));

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        mimeMessage.setContent(multipart);

        System.out.println(builder.toString());

        mailSender.send(mimeMessage);
        System.out.println("Calendar invite sent");

    }

}