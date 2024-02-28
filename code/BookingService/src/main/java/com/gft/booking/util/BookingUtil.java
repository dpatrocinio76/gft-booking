package com.gft.booking.util;

public class BookingUtil {

    private static Integer mockCheckinNumber = 1543;

    public static Integer getMockCheckinNumber() {
        return mockCheckinNumber++;
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.7;
    }

}
