package com.example.ezjob.exception;

import java.io.Serial;

public class ResumeNotFoundException extends UserNotFoundException{
    @Serial
    private static final long serialVersionUID = 227061286141084114L;

    public ResumeNotFoundException(String msg) {
        super(msg);
    }
}
