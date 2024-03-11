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

    public List<BookingDTO> getAllBookings(){
        return bookingClient.getAllBookings();
    }

    public void changeBooking(BookingDTO bookingDTO, String id){
        bookingClient.changeBookingClient(bookingDTO, id);
    }

    public void removeBooking(String id) {
        bookingClient.deleteBookingClient(id);
    }

    public void setBookingClient(BookingClient bookingClient) {
        this.bookingClient = bookingClient;
    }

}
