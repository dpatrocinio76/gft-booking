package com.gft.booking.notification;

import com.gft.booking.dto.BookingDTO;

public interface BookingExternalNotificationService {

    public void sendNotification(BookingDTO bookingDTO, String message) throws Exception;

}
