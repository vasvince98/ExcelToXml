package com.edti.exceltoxml.Models;

import org.apache.poi.ss.usermodel.Cell;

public class Dummy {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private double bankAccount;
    private String username;
    private boolean customer;

    public Dummy() {}

    public Dummy(String firstName, String lastName, String email, int age, double bankAccount, String username, boolean customer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.bankAccount = bankAccount;
        this.username = username;
        this.customer = customer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(double bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Dummy{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", bankAccount=" + bankAccount +
                ", username='" + username + '\'' +
                ", customer=" + customer +
                '}';
    }
}
