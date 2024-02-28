package com.gft.bookingapi.client;

import com.gft.bookingapi.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class BookingClient {

    private final String POSTS_STORE_SERVICE_URI = "http://localhost:8080/booking";

    @Autowired
    RestTemplate restTemplate;

    public List<BookingDTO> bookingClient(){
        ResponseEntity<BookingDTO[]> responseEntity = restTemplate.getForEntity(POSTS_STORE_SERVICE_URI+"/reservations", BookingDTO[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public void changeBookingClient(BookingDTO bookingDTO, String id){
        restTemplate.put(POSTS_STORE_SERVICE_URI+"/"+id, bookingDTO, BookingDTO.class);
    }

    public void deleteBookingClient(String id){
        restTemplate.delete(POSTS_STORE_SERVICE_URI+"/"+id);
    }

}
