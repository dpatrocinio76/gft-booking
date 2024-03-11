package com.gft.bookingapi;

import com.gft.bookingapi.client.BookingClient;
import com.gft.bookingapi.dto.BookingDTO;
import com.gft.bookingapi.service.BookingService;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookingApiApplicationTests {

	@Mock
	BookingClient bookingClientMock;

	BookingService bookingService;

	@BeforeEach
	public void initEach(){
		Mockito.when(bookingClientMock.getAllBookings()).thenReturn(new ArrayList<BookingDTO>());
		Mockito.doNothing().when(bookingClientMock).changeBookingClient(Mockito.any(BookingDTO.class), Mockito.anyString());
		Mockito.doNothing().when(bookingClientMock).deleteBookingClient(Mockito.anyString());

		bookingService = new BookingService();
		bookingService.setBookingClient(bookingClientMock);
	}

	@Test
	void bookingServiceGetAllBookingsTest() {
		List<BookingDTO> allBookings = bookingService.getAllBookings();
		Assertions.assertTrue(allBookings.isEmpty());
	}

	@Test
	void bookingServiceChangeBookingClientTest() {
		bookingService.changeBooking(new BookingDTO(), "");
		Assertions.assertTrue(true);
	}

	@Test
	void bookingServiceRemoveBookingClientTest() {
		bookingService.removeBooking("");
		Assertions.assertTrue(true);
	}

}
