package com.netcracker.hackathon.smartwfm.admin.service;
import java.time.LocalDateTime;
import java.util.UUID;

public class CalendarRequest {
    private String uid = UUID.randomUUID().toString();
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
    private LocalDateTime meetingStartTime;
    private LocalDateTime meetingEndTime;

    private CalendarRequest(Builder builder) {
        fromEmail = builder.fromEmail;
        toEmail = builder.toEmail;
        subject = builder.subject;
        body = builder.body;
        meetingStartTime = builder.meetingStartTime;
        meetingEndTime = builder.meetingEndTime;
    }


    public String getUid() {
        return uid;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getMeetingStartTime() {
        return meetingStartTime;
    }

    public LocalDateTime getMeetingEndTime() {
        return meetingEndTime;
    }

    public static final class Builder {
        private String fromEmail;
        private String toEmail;
        private String subject;
        private String body;
        private LocalDateTime meetingStartTime;
        private LocalDateTime meetingEndTime;

        public Builder() {
        }

        public Builder withFromEmail(String val) {
            fromEmail = val;
            return this;
        }
        public Builder withToEmail(String val) {
            toEmail = val;
            return this;
        }

        public Builder withSubject(String val) {
            subject = val;
            return this;
        }

        public Builder withBody(String val) {
            body = val;
            return this;
        }

        public Builder withMeetingStartTime(LocalDateTime val) {
            meetingStartTime = val;
            return this;
        }

        public Builder withMeetingEndTime(LocalDateTime val) {
            meetingEndTime = val;
            return this;
        }

        public CalendarRequest build() {
            return new CalendarRequest(this);
        }
    }
}

