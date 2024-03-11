package com.gft.bookingapi.message;

import com.gft.bookingapi.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMessage {

    @Autowired
    private KafkaTemplate<String, BookingDTO> kafkaTemplate;

    @Value("${booking.topic}")
    private String KAFKA_TOPIC;


    public void sendMessage(BookingDTO bookingDTO){
        kafkaTemplate.send(KAFKA_TOPIC, bookingDTO);
    }

}
