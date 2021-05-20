package com.example.bank.model;

import java.io.Serializable;

public class UserResResponse implements Serializable {
    private String message;

    public UserResResponse(String message) {
        this.message = message;
    }

    public UserResResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
