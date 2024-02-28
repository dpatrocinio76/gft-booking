package com.gft.booking.exception;

public class EmailExternalBookingNotificationException extends Exception {

    @Override
    public String getMessage() {
        return "External e-mail booking notification service is unavailable. Try again later";
    }
}
