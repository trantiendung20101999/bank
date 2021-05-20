package com.example.bank.model;

import java.io.Serializable;

public class UserSaveBook implements Serializable {
    private long id;

    public UserSaveBook(long id) {
        this.id = id;
    }

    public UserSaveBook() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
