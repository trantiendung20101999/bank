package com.example.bank.model;

import java.io.Serializable;

public class InterestRateSaveBook implements Serializable {
    private long id;
    private int times;

    public InterestRateSaveBook(int times) {
        this.times = times;
    }

    public InterestRateSaveBook(long id, int times) {
        this.id = id;
        this.times = times;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
