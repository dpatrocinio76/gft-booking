package com.gft.booking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="booking")
@Data
@NoArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "personal_number")
    private String personalNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "checkin_date")
    private String checkinDate;

    @Column(name = "checkout_date")
    private String checkoutDate;

    @Column(name = "hotel_checkin_number")
    private String hotelCheckinNumber;

    @Column(name = "car_checkin_number")
    private String carCheckinNumber;

    @Column(name = "flight_checkin_number")
    private String flightCheckinNumber;

}
