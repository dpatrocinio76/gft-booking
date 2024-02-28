package com.gft.booking.respository;

import com.gft.booking.dto.BookingDTO;

public interface ExternalBookingRepository {

    public boolean createBookingExternalIntegratedWebService(BookingDTO bookingDTO) throws Exception;

    public void changeBookingExternalIntegratedService(BookingDTO bookingDTO) throws Exception;

    public void removeBookingExternalIntegratedService(BookingDTO bookingDTO) throws Exception;

}
