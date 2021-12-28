package com.matusek.basic;

import java.time.*;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class C08_DatTime {

    public static void main(String... strings) {

        LocalDate.now();

        LocalDate.parse("1999-11-25").getDayOfMonth();

        LocalDateTime.now().plus(Duration.ofHours(96));

        LocalDateTime.now().minus(1, ChronoUnit.DAYS);

        Chronology.getAvailableChronologies();

        LocalDateTime.now().getChronology();

        System.out.println(LocalDateTime.MAX);

        System.out.println(LocalDate.MAX);

        System.out.println(LocalTime.MAX);

        // Time zones

        ZoneId.getAvailableZoneIds();

        System.out.println(ZoneId.of("Europe/Paris"));

        System.out.println(ZoneOffset.of("+02:00"));

        // Date format

        LocalDateTime.of(1990, Month.APRIL, 15, 0, 0, 0).format(DateTimeFormatter.ofPattern("YYYY|dd|MM"));

        // Duration - difference between date times

        LocalDateTime tokenCrated = LocalDateTime.of(2020, 1, 1, 10, 0, 0);

        LocalDateTime tokenValidTill = LocalDateTime.of(2020, 1, 1, 10, 0, 10);

        Duration duration = Duration.between(tokenCrated, tokenValidTill);

        System.out.println(duration.toSeconds());

        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)); // Timestamp in long


        // Period - difference between dates

        System.out.println(Period.between(LocalDate.of(2020, 1, 10), LocalDate.of(2020, 1, 1)).getDays());

        // Date and Calendar to LocalDate

        LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

        long timestamp = System.currentTimeMillis();
        LocalDateTime triggerTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());

        System.out.println(triggerTime);
    }
}
