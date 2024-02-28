package com.gft.booking.message;

import com.gft.booking.service.BookingService;
import com.gft.booking.dto.BookingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private BookingService bookingService;

    @KafkaListener(topics = "booking-topic", groupId = "booking-group")
    public void listening(BookingDTO bookingDTO) {
        LOG.info("Received Booking information from KAFKA booking-topic: {}", bookingDTO);
        bookingService.createBooking(bookingDTO);
    }

}
