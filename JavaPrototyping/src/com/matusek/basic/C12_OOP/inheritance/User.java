package com.matusek.basic.C12_OOP.inheritance;

public abstract class User {

    private int id;

    public User(int id) {
        this.id = id;
    }

    public String tellMeAbout() {
        return "I am user";
    }

    public int getId() {
        return id;
    }
}
