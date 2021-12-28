package com.matusek.hackerrank;

public class P005_TimeConversion {

    // 12:00:00AM => 00:00:00
    // 12:00:00PM => 12:00:00

    public static void main(String[] args) {
        System.out.println(String.format("%s %s", "12:00:00AM", format("12:00:00AM")));
        System.out.println(String.format("%s %s", "12:00:00PM", format("12:00:00PM")));
        System.out.println(String.format("%s %s", "05:45:59AM", format("05:45:59AM")));
        System.out.println(String.format("%s %s", "05:45:59PM", format("05:45:59PM")));
        System.out.println(String.format("%s %s", "11:59:59AM", format("11:59:59AM")));
        System.out.println(String.format("%s %s", "11:59:59PM", format("11:59:59PM")));

        System.out.println();
        System.out.println(String.format("%s %s", "07:05:45PM", format("07:05:45PM")));
    }

    static String format(String s) {
        String[] parts = s.split(":");

        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2].substring(0, parts[2].length() - 2));

        hours += s.contains("PM") ? 12 : 0;
        if (s.contains("AM") && hours == 12)
            hours = 0;
        if (s.contains("PM") && hours == 24)
            hours = 12;

        String hoursStr = hours < 10 ? "0" + hours : String.valueOf(hours);
        String minutesStr = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
        String secondsStr = seconds < 10 ? "0" + seconds : String.valueOf(seconds);

        return hoursStr + ":" + minutesStr + ":" + secondsStr;
    }
}
