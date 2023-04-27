package com.example.weatherproject.exception;

import com.google.gson.annotations.Expose;

public class ExceptionBody {

    @Expose
    private int status;

    @Expose
    private String message;

    public ExceptionBody() {
    }

    public ExceptionBody(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
