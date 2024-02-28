package com.gft.bookingapi.service;

import com.gft.bookingapi.client.BookingClient;
import com.gft.bookingapi.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingClient bookingClient;

    public List<BookingDTO> getReservations(){
        return bookingClient.bookingClient();
    }

    public void changeBooking(BookingDTO carPostDTO, String id){
        bookingClient.changeBookingClient(carPostDTO, id);
    }

    public void removeBooking(String id) {
        bookingClient.deleteBookingClient(id);
    }

}
