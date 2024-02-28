package com.gft.booking.respository.impl;


import com.gft.booking.dto.BookingDTO;
import com.gft.booking.respository.ExternalBookingRepository;
import com.gft.booking.util.BookingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CarExternalBookingWebServiceRepositoryImpl implements ExternalBookingRepository {

    private final Logger LOG = LoggerFactory.getLogger(CarExternalBookingWebServiceRepositoryImpl.class);

    public final String urlExternalCarBookingService = "http://rentcar...";

    @Override
    public boolean createBookingExternalIntegratedWebService(BookingDTO bookingDTO) {
        // TODO: implement a Integration with a real service to booking using the URL: urlExternalHotelBookingService. Below mock was created.
        boolean bookingSuccess = BookingUtil.getRandomBoolean(); // Simulating returns successes or not

        if (bookingSuccess) {
            bookingDTO.setCarCheckinNumber(BookingUtil.getMockCheckinNumber().toString());
            LOG.info("Car Booking was created in web service");
        } else {
            LOG.info("Car Booking was NOT created in web service");
        }

        return bookingSuccess;
    }

    @Override
    public void changeBookingExternalIntegratedService(BookingDTO bookingDTO) {
        // TODO: implement a Integration with a real service to booking using the URL: urlExternalHotelBookingService.
        LOG.info("Car Booking was changed in web service");
    }

    @Override
    public void removeBookingExternalIntegratedService(BookingDTO bookingDTO) {
        // TODO: implement a Integration with a real service to booking using the URL: urlExternalHotelBookingService.
        LOG.info("Car Booking was removed in web service");
    }
}
