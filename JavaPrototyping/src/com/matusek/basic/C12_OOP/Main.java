package com.matusek.basic.C12_OOP;

import com.matusek.basic.C12_OOP.composition.Computer;
import com.matusek.basic.C12_OOP.inheritance.Programmer;
import com.matusek.basic.C12_OOP.inheritance.Redactor;
import com.matusek.basic.C12_OOP.inheritance.User;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // INHERITANCE: Programmer is-a user, Redactor is-a user
        User programmer = new Programmer(1, Arrays.asList("Java", "C++"));
        User redactor = new Redactor(2, Arrays.asList("What we know about life ?", "Future on Mars"));
        System.out.println(programmer.tellMeAbout());
        System.out.println(redactor.tellMeAbout());
        printUsersId(programmer);

        // COMPOSITION
        Computer computer = new Computer();
        computer.calculateNumber(4124);
        computer.storeValue("Name", "Tomas");
    }

    // Polymorphism
    private static void printUsersId(User user) {
        System.out.println(user.getId());
    }
}
