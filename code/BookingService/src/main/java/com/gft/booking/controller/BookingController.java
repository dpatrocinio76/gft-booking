package com.gft.booking.controller;


import com.gft.booking.dto.BookingDTO;
import com.gft.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAllBookings(){
        return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.getAllBookings());
    }

    @PutMapping("/{id}")
    public ResponseEntity changeBooking(@RequestBody BookingDTO bookingDTO, @PathVariable("id") String id){
        bookingService.changeBooking(bookingDTO, Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeBooking(@PathVariable("id") String id){
        bookingService.removeBooking(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
