package com.gft.bookingapi.controller;

import com.gft.bookingapi.dto.BookingDTO;
import com.gft.bookingapi.message.KafkaProducerMessage;
import com.gft.bookingapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity postBooking(@Valid @RequestBody BookingDTO bookingDTO) throws Exception {
        rulesValidations(bookingDTO);
        kafkaProducerMessage.sendMessage(bookingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAllBookings(){
        return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.getAllBookings());
    }

    @PutMapping("/{id}")
    public ResponseEntity changeBooking(@RequestBody BookingDTO bookingDTO, @PathVariable("id") String id){
        bookingService.changeBooking(bookingDTO,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBooking(@PathVariable("id") String id){
        bookingService.removeBooking(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void rulesValidations(BookingDTO bookingDTO) throws Exception {
        // TODO: implements rules validations. For example: checking and checkout dates, already exists, etc
    }

}
