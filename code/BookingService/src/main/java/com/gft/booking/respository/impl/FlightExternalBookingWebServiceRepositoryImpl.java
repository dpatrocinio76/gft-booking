package com.gft.booking.respository.impl;


import com.gft.booking.dto.BookingDTO;
import com.gft.booking.respository.ExternalBookingRepository;
import com.gft.booking.util.BookingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FlightExternalBookingWebServiceRepositoryImpl implements ExternalBookingRepository {

    private final Logger LOG = LoggerFactory.getLogger(CarExternalBookingWebServiceRepositoryImpl.class);

    public final String urlExternalHotelBookingService = "http://flights...";

    @Override
    public boolean createBookingExternalIntegratedWebService(BookingDTO bookingDTO) {
        // TODO: implement a Integration with a real service to booking using the URL: urlExternalHotelBookingService. Below a mock were created.

        boolean bookingSuccess = BookingUtil.getRandomBoolean(); // Simulating returns successes or not

        if (bookingSuccess) {
            bookingDTO.setFlightCheckinNumber(BookingUtil.getMockCheckinNumber().toString());
            LOG.info("Flight Booking was created in web service");
        } else {
            LOG.info("Flight Booking was NOT created in web service");
        }

        return bookingSuccess;
    }

    @Override
    public void changeBookingExternalIntegratedService(BookingDTO bookingDTO) {
        // TODO: implement a Integration with a real service to booking using the URL: urlExternalHotelBookingService.
        LOG.info("Flight Booking was changed in web service");
    }

    @Override
    public void removeBookingExternalIntegratedService(BookingDTO bookingDTO) {
        // TODO: implement a Integration with a real service to booking using the URL: urlExternalHotelBookingService.
        LOG.info("Flight Booking was removed in web service");
    }
}
