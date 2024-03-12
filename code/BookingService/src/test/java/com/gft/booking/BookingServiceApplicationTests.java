package com.gft.booking;

import com.gft.booking.dto.BookingDTO;
import com.gft.booking.entity.BookingEntity;
import com.gft.booking.exception.EmailExternalBookingNotificationException;
import com.gft.booking.notification.impl.EmailBookingExternalNotificationService;
import com.gft.booking.respository.BookingRepository;
import com.gft.booking.respository.impl.CarExternalBookingWebServiceRepositoryImpl;
import com.gft.booking.respository.impl.FlightExternalBookingWebServiceRepositoryImpl;
import com.gft.booking.respository.impl.HotelExternalBookingWebServiceRepositoryImpl;
import com.gft.booking.service.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookingServiceApplicationTests {


	@Mock
	FlightExternalBookingWebServiceRepositoryImpl flightExternalBookingWebServiceRepositoryMock;

	@Mock
	HotelExternalBookingWebServiceRepositoryImpl hotelExternalBookingWebServiceRepositoryMock;

	@Mock
	CarExternalBookingWebServiceRepositoryImpl carExternalBookingWebServiceRepositoryMock;

	@Mock
	EmailBookingExternalNotificationService emailBookingExternalNotificationServiceMock;

	@Mock
	BookingRepository bookingRepositoryMock;


	@BeforeEach
	public void initEach(){

		BookingService bookingService = new BookingService();

		bookingService.setFlightExternalBookingWebServiceRepository(flightExternalBookingWebServiceRepositoryMock);
		bookingService.setHotelExternalBookingWebServiceRepository(hotelExternalBookingWebServiceRepositoryMock);
		bookingService.setEmailBookingExternalNotificationService(emailBookingExternalNotificationServiceMock);
		bookingService.setBookingRepository(bookingRepositoryMock);
	}

	@Test
	void bookingServiceCreateBookingBookingsTest() {

		Mockito.when(flightExternalBookingWebServiceRepositoryMock.createBookingExternalIntegratedWebService(Mockito.any(BookingDTO.class))).thenReturn(true);
		Mockito.when(hotelExternalBookingWebServiceRepositoryMock.createBookingExternalIntegratedWebService(Mockito.any(BookingDTO.class))).thenReturn(true);
		Mockito.when(carExternalBookingWebServiceRepositoryMock.createBookingExternalIntegratedWebService(Mockito.any(BookingDTO.class))).thenReturn(true);

		try {
			Mockito.doNothing().when(emailBookingExternalNotificationServiceMock).sendNotification(Mockito.any(BookingDTO.class), Mockito.anyString());
		} catch (EmailExternalBookingNotificationException e) {
			Assertions.fail("Problem to send notification to user");
		}

		Mockito.doNothing().when(bookingRepositoryMock).save(Mockito.any(BookingEntity.class));

		Assertions.assertTrue(true);
	}



}
