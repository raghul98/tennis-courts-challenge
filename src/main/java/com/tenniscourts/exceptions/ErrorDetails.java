package com.tenniscourts.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    
    public ErrorDetails(LocalDateTime time,String msg,String des) {
    	timestamp = time;
    	message = msg;
    	details = des;
    }
}
