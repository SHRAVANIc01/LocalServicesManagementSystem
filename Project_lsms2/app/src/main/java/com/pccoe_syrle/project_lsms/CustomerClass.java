package com.pccoe_syrle.project_lsms;

public class CustomerClass {

    private String name,email,address,username;
    long phone, balance;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public CustomerClass(String name, String email, String address, String username, long phone, long balance) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.username = username;
        this.phone = phone;
        this.balance = balance;
    }

    public CustomerClass(String name, String email, String address, String username, long phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.username = username;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CustomerClass() {
        this.name = "name";
        this.email = "email";
        this.address = "address";
        this.phone = 0;
    }

    public CustomerClass(String name, String email, String address, long phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getLongPhone() {
        return phone;
    }

    public String getPhone() {
        return phone+"";
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
