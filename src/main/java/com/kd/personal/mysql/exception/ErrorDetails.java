package com.kd.personal.mysql.exception;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ErrorDetails {
    private Date timestamp;
    private List<String> message;
    private String details;
    public ErrorDetails(Date timestamp,List<String> message,String details){
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
