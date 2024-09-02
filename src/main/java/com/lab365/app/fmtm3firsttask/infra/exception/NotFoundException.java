package com.lab365.app.fmtm3firsttask.infra.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
