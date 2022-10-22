package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.util.Optional;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // NON-BLOCKING executed when event is emitted -> We need that System.in.read()
        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(number -> System.out.println(number));

        // BLOCKING -> Code is waiting at this point for value
        // Get the value from the Mono into an integer variable
        // You are getting one event in the future and you are handling that when it happens
        Integer number = ReactiveSources.intNumberMono().block(); // number emitted by Mono
        Optional<Integer> number2 = ReactiveSources.intNumberMono().blockOptional();

        // FLUX -> List of value -> When Flux emits a value you don't know if it is last value or not, thre can be another value i future -> non blocking
        // MONO -> When Mono emits a value then it is DONE there aren't gona be another value

        System.out.println("Press a key to end");
        System.in.read();
    }
}