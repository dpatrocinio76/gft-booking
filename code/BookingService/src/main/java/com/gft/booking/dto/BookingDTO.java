package com.gft.booking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class BookingDTO {

    private Long id;
    private String name;
    private String email;
    private String personalNumber;
    private String city;
    private String state;
    private String checkinDate;
    private String checkoutDate;
    private String hotelCheckinNumber;
    private String carCheckinNumber;
    private String flightCheckinNumber;
}
