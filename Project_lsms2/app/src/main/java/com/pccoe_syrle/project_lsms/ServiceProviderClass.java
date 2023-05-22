package com.pccoe_syrle.project_lsms;

public class ServiceProviderClass {
    String name,email,address,service;
    long phone;

    public ServiceProviderClass() {
        this.name = "hello";
        this.email = "hello";
        this.address = "hello";
        this.service = "hello";
        this.phone = 999999999;
    }

    public ServiceProviderClass(String name, String email, String address, String service, long phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.service = service;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
