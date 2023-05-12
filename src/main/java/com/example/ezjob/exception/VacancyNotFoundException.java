package com.example.ezjob.exception;

import java.io.Serial;

public class VacancyNotFoundException extends NotFoundException{
    @Serial
    private static final long serialVersionUID = -250630832058198486L;

    public VacancyNotFoundException(String msg) {
        super(msg);
    }
}
