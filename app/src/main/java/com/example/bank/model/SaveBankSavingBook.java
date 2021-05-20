package com.example.bank.model;

public class SaveBankSavingBook {

    private double money;
    private String branch;
    private InterestRateSaveBook interestrate;
    private UserSaveBook user;

    public SaveBankSavingBook() {
    }

    public SaveBankSavingBook(double money, String branch, InterestRateSaveBook interestrate, UserSaveBook user) {
        this.money = money;
        this.branch = branch;
        this.interestrate = interestrate;
        this.user = user;
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

    public InterestRateSaveBook getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(InterestRateSaveBook interestrate) {
        this.interestrate = interestrate;
    }

    public UserSaveBook getUser() {
        return user;
    }

    public void setUser(UserSaveBook user) {
        this.user = user;
    }
}
