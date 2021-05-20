package com.example.bank.model;

import java.io.Serializable;

public class InterestrateBankSavingBookResponse implements Serializable {
    private long id;
    private String typeOfSaving;
    private int times;
    private double interestRate;

    public InterestrateBankSavingBookResponse() {
    }

    public InterestrateBankSavingBookResponse(long id, String typeOfSaving, int times, double interestRate) {
        this.id = id;
        this.typeOfSaving = typeOfSaving;
        this.times = times;
        this.interestRate = interestRate;
    }

    public InterestrateBankSavingBookResponse(String typeOfSaving, int times, double interestRate) {
        this.typeOfSaving = typeOfSaving;
        this.times = times;
        this.interestRate = interestRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeOfSaving() {
        return typeOfSaving;
    }

    public void setTypeOfSaving(String typeOfSaving) {
        this.typeOfSaving = typeOfSaving;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
