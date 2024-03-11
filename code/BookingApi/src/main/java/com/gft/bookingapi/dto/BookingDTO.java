package com.gft.bookingapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class BookingDTO {

    private Long id;
    private String name;
    private String email;

    @NotNull
    @NotBlank
    private String personalNumber;
    private String city;
    private String state;

    @NotNull
    @NotBlank
    private String checkinDate;

    @NotNull
    @NotBlank
    private String checkoutDate;
    private String hotelCheckinNumber;
    private String carCheckinNumber;
    private String flightCheckinNumber;
    
}
