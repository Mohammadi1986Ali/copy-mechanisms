package com.ai4everyone.tutorial.copymechanisms.model;

public class User {
    private String name;
    private String family;
    private Address address;

    public User(String name, String family, Address address) {
        this.name = name;
        this.family = family;
        this.address = address;
    }

    public User(User that) {
        this(that.getName(), that.getFamily(), new Address(that.getAddress()));
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
