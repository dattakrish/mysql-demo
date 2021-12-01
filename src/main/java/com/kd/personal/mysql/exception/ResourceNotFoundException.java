package com.kd.personal.mysql.exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 2135232836950604124L;
    public ResourceNotFoundException(String message){
        super(message);
    }
}
