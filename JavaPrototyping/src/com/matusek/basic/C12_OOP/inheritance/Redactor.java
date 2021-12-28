package com.matusek.basic.C12_OOP.inheritance;

import java.util.ArrayList;
import java.util.List;

public class Redactor extends User {

    public List<String> articles = new ArrayList<>();

    public Redactor(int id, List<String> articles) {
        super(id);
        this.articles = articles;
    }

    public List<String> getArticles() {
        return articles;
    }

    @Override
    public String tellMeAbout() {
        return "I am Redactor, my articles are " + getArticles().toString();
    }
}
