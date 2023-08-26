package com.netcracker.hackathon.smartwfm.wfm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InterviewScheduleNotFoundException extends RuntimeException {
    public InterviewScheduleNotFoundException(String message) {
        super(message);
    }
}
