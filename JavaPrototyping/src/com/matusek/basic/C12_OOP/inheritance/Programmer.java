package com.matusek.basic.C12_OOP.inheritance;

import java.util.ArrayList;
import java.util.List;

public class Programmer extends User {

    private List<String> languages = new ArrayList<>();

    public Programmer(int id, List<String> languages) {
        super(id);
        this.languages = languages;
    }

    public void addLanguage(String l) {
        languages.add(l);
    }

    public List<String> getLanguages() {
        return languages;
    }

    @Override
    public String tellMeAbout() {
        return "I am Programmer and I program in " + getLanguages().toString();
    }
}