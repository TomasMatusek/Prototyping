package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

public class Exercise2 {

    public static void main(String[] args) throws IOException {


        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // Print all numbers in the ReactiveSources.intNumbersFlux stream
        Flux<Integer> events = Flux
                .range(1, 10)
                .delayElements(Duration.ofMillis(500));

        events.subscribe(event -> System.out.println("Event " + event)); // whenever event happens, is emitted, run this thing
        // subscribe is controlled by event emitter in this case 'events' Flux


        // Print all users in the ReactiveSources.userFlux stream
        ReactiveSources.userFlux().subscribe(user -> System.out.println(user));

        // If this is commented out it won't print anything because process is killed (main is done)
        // We are hanging out so events can be emitted
        System.out.println("Press a key to end");
        System.in.read();

    }

}