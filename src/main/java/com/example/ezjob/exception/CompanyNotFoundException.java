package com.example.ezjob.exception;

import java.io.Serial;

public class CompanyNotFoundException extends NotFoundException{
    @Serial
    private static final long serialVersionUID = -2480416950910659138L;

    public CompanyNotFoundException(String msg) {
        super(msg);
    }
}
