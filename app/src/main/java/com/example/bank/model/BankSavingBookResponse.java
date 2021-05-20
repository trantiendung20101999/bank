package com.example.bank.model;

import java.io.Serializable;

public class BankSavingBookResponse implements Serializable {
    private long id;
    private double money;
    private String branch;
    private String startDate;
    private InterestrateBankSavingBookResponse interestrate;
    private UserBankSavingBookResponse user;

    public BankSavingBookResponse() {
    }

    public BankSavingBookResponse(long id, double money, String branch, String startDate, InterestrateBankSavingBookResponse interestrate, UserBankSavingBookResponse user) {
        this.id = id;
        this.money = money;
        this.branch = branch;
        this.startDate = startDate;
        this.interestrate = interestrate;
        this.user = user;
    }

    public BankSavingBookResponse(double money, String branch, String startDate, InterestrateBankSavingBookResponse interestrate, UserBankSavingBookResponse user) {
        this.money = money;
        this.branch = branch;
        this.startDate = startDate;
        this.interestrate = interestrate;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public InterestrateBankSavingBookResponse getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(InterestrateBankSavingBookResponse interestrate) {
        this.interestrate = interestrate;
    }

    public UserBankSavingBookResponse getUser() {
        return user;
    }

    public void setUser(UserBankSavingBookResponse user) {
        this.user = user;
    }
}
