package com.ai4everyone.tutorial.copymechanisms.model;

import java.io.Serializable;

public class Account implements Cloneable, Serializable {
    private String accountId;
    private boolean isActive;

    public Account() {
    }

    public Account(String accountId, boolean isActive) {
        this.accountId = accountId;
        this.isActive = isActive;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Account(this.accountId, this.isActive);
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
