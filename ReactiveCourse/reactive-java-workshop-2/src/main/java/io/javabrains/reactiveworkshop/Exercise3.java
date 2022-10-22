package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Exercise3 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()
        List<Integer> numbers = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .toStream()
                .toList();

        // Blocking operation, it is waiting until 10 seconds and events are aggregated
        System.out.println("List: " + numbers);
        System.out.println("List size: " + numbers.size());

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
        // TODO: Write code here
    }

}
