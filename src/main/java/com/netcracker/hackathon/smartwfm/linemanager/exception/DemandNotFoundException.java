package com.netcracker.hackathon.smartwfm.linemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DemandNotFoundException extends RuntimeException {
    public DemandNotFoundException(String message) {
        super(message);
    }
}
