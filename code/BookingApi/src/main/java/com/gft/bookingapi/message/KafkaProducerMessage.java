package com.gft.bookingapi.message;

import com.gft.bookingapi.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMessage {

    @Autowired
    private KafkaTemplate<String, BookingDTO> kafkaTemplate;

    private final String KAFKA_TOPIC = "booking-topic";

    public void sendMessage(BookingDTO bookingDTO){
        kafkaTemplate.send(KAFKA_TOPIC, bookingDTO);
    }

}
