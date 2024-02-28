package com.gft.booking.notification.impl;

import com.gft.booking.dto.BookingDTO;
import com.gft.booking.exception.EmailExternalBookingNotificationException;
import com.gft.booking.notification.BookingExternalNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailBookingExternalNotificationService implements BookingExternalNotificationService {

    private final Logger LOG = LoggerFactory.getLogger(EmailBookingExternalNotificationService.class);

    private final String urlEmailService = "SMTP service...";

    @Override
    public void sendNotification(BookingDTO bookingDTO, String message) throws EmailExternalBookingNotificationException {
        // TODO: integration with a real SMTP service to booking using the URL: urlEmailService. Below, mocks were created in methods.
        LOG.info("Send e-mail to: " + bookingDTO.getEmail() + " - Message content: " + message + " - Please, contact your booking agent");
    }
}
