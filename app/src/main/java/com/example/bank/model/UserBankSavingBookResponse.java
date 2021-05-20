package com.example.bank.model;

import java.io.Serializable;
import java.util.List;

public class UserBankSavingBookResponse implements Serializable {
    private long id;
    private String username;
    private String password;
    private String fullName;
    private String address;
    private double money;
    private String dob;
    private String phone;
    private String email;
    private List<Role> roles;
    private String idCard;
    private String facebookId;

    public UserBankSavingBookResponse() {
    }

    public UserBankSavingBookResponse(long id, String username, String password, String fullName, String address, double money, String dob, String phone, String email, List<Role> roles, String idCard, String facebookId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.money = money;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
        this.idCard = idCard;
        this.facebookId = facebookId;
    }

    public UserBankSavingBookResponse(String username, String password, String fullName, String address, double money, String dob, String phone, String email, List<Role> roles, String idCard, String facebookId) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.money = money;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
        this.idCard = idCard;
        this.facebookId = facebookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
