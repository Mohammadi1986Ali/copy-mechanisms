package com.ai4everyone.tutorial.copymechanisms.model;

import java.io.Serializable;

public class Customer implements Cloneable, Serializable {
    private String name;
    private String family;
    private Account account;

    public Customer() {
    }

    public Customer(String name, String family, Account account) {
        this.name = name;
        this.family = family;
        this.account = account;
    }

    @Override
    public Object clone() {
        Customer customer;
        try {
            customer = (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            customer = new Customer(this.getName(), this.getFamily(), this.getAccount());
        }
        customer.account = (Account) this.account.clone();
        return customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
