package com.gft.booking.service;

import com.gft.booking.notification.impl.EmailBookingExternalNotificationService;
import com.gft.booking.respository.BookingRepository;
import com.gft.booking.dto.BookingDTO;
import com.gft.booking.entity.BookingEntity;
import com.gft.booking.respository.impl.CarExternalBookingWebServiceRepositoryImpl;
import com.gft.booking.respository.impl.FlightExternalBookingWebServiceRepositoryImpl;
import com.gft.booking.respository.impl.HotelExternalBookingWebServiceRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightExternalBookingWebServiceRepositoryImpl flightExternalBookingWebServiceRepository;

    @Autowired
    private HotelExternalBookingWebServiceRepositoryImpl hotelExternalBookingWebServiceRepository;

    @Autowired
    private CarExternalBookingWebServiceRepositoryImpl carExternalBookingWebServiceRepository;

    @Autowired
    private EmailBookingExternalNotificationService emailBookingExternalNotificationService;


    public void createBooking(BookingDTO bookingDTO) {

        boolean flightBookingSuccess = flightExternalBookingWebServiceRepository.createBookingExternalIntegratedWebService(bookingDTO);
        boolean hotelBookingSuccess = hotelExternalBookingWebServiceRepository.createBookingExternalIntegratedWebService(bookingDTO);;
        boolean carBookingSuccess = carExternalBookingWebServiceRepository.createBookingExternalIntegratedWebService(bookingDTO);;

        userNotify(bookingDTO, flightBookingSuccess, hotelBookingSuccess, carBookingSuccess);

        persistBooking(bookingDTO);
    }

    private void persistBooking(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = mapBookingDtoToEntity(bookingDTO);
        bookingRepository.save(bookingEntity);
    }

    private void userNotify(BookingDTO bookingDTO, boolean flightBookingSuccess, boolean hotelBookingSuccess, boolean carBookingSuccess) {
        if(flightBookingSuccess)
            tyrSendBookingNotification(bookingDTO, "Flight booking was made successfully! The checking flight number is: " + bookingDTO.getFlightCheckinNumber());
        else
            tyrSendBookingNotification(bookingDTO, "Was not possible to booking the flight in web service!");

        if (hotelBookingSuccess)
            tyrSendBookingNotification(bookingDTO, "Hotel booking was made successfully! The checking hotel number is: " + bookingDTO.getHotelCheckinNumber());
        else
            tyrSendBookingNotification(bookingDTO, "Was not possible to booking the hotel in web service!");

        if (carBookingSuccess)
            tyrSendBookingNotification(bookingDTO, "Car booking was made successfully! The checking car number is: " + bookingDTO.getCarCheckinNumber());
        else
            tyrSendBookingNotification(bookingDTO, "Was not possible to booking the car in web service!");
    }

    private void tyrSendBookingNotification(BookingDTO bookingDTO, String message) {
        try {
            emailBookingExternalNotificationService.sendNotification(bookingDTO, message);
        } catch (Exception notificationException) {
            // TODO: implement a mechanism to retry sent notification to the user;
        }
    }

    public List<BookingDTO> getReservations() {
        List<BookingDTO> listReservations = new ArrayList<>();
        bookingRepository.findAll().forEach(item->{
            listReservations.add(mapBookingEntityToDTO(item));
        });
        return listReservations;
    }

    public void changeBooking(BookingDTO bookingDTO, Long bookingId) {
        bookingRepository.findById(bookingId).ifPresentOrElse(bookingItem->{

            flightExternalBookingWebServiceRepository.changeBookingExternalIntegratedService(bookingDTO);
            hotelExternalBookingWebServiceRepository.changeBookingExternalIntegratedService(bookingDTO);
            carExternalBookingWebServiceRepository.changeBookingExternalIntegratedService(bookingDTO);

            bookingItem.setName(bookingDTO.getName());
            bookingItem.setEmail(bookingDTO.getEmail());
            bookingItem.setPersonalNumber(bookingDTO.getPersonalNumber());
            bookingItem.setCity(bookingDTO.getCity());
            bookingItem.setState(bookingDTO.getState());
            bookingItem.setCheckinDate(bookingDTO.getCheckinDate());
            bookingItem.setCheckoutDate(bookingDTO.getCheckoutDate());

            bookingRepository.save(bookingItem);

        }, ()-> {
            throw new NoSuchElementException();
        });
    }

    public void removeBooking(Long bookingId) {
        bookingRepository.findById(bookingId).ifPresentOrElse(bookingItem->{

            BookingDTO bookingDTO = mapBookingEntityToDTO(bookingItem);
            flightExternalBookingWebServiceRepository.removeBookingExternalIntegratedService(bookingDTO);
            hotelExternalBookingWebServiceRepository.removeBookingExternalIntegratedService(bookingDTO);
            carExternalBookingWebServiceRepository.removeBookingExternalIntegratedService(bookingDTO);

        }, ()-> {
            throw new NoSuchElementException();
        });
        bookingRepository.deleteById(bookingId);
    }

    private BookingEntity mapBookingDtoToEntity(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setName(bookingDTO.getName());
        bookingEntity.setEmail(bookingDTO.getEmail());
        bookingEntity.setPersonalNumber(bookingDTO.getPersonalNumber());
        bookingEntity.setCity(bookingDTO.getCity());
        bookingEntity.setState(bookingDTO.getState());
        bookingEntity.setCheckinDate(bookingDTO.getCheckinDate());
        bookingEntity.setCheckoutDate(bookingDTO.getCheckoutDate());
        bookingEntity.setHotelCheckinNumber(bookingDTO.getHotelCheckinNumber());
        bookingEntity.setFlightCheckinNumber(bookingDTO.getFlightCheckinNumber());
        bookingEntity.setCarCheckinNumber(bookingDTO.getCarCheckinNumber());

        return bookingEntity;
    }

    private BookingDTO mapBookingEntityToDTO(BookingEntity bookingEntity){

        return BookingDTO.builder()
                    .id(bookingEntity.getId())
                    .name(bookingEntity.getName())
                    .email(bookingEntity.getEmail())
                    .personalNumber(bookingEntity.getPersonalNumber())
                    .city(bookingEntity.getCity())
                    .state(bookingEntity.getState())
                    .checkinDate(bookingEntity.getCheckinDate())
                    .checkoutDate(bookingEntity.getCheckoutDate())
                    .flightCheckinNumber(bookingEntity.getFlightCheckinNumber())
                    .hotelCheckinNumber(bookingEntity.getHotelCheckinNumber())
                    .carCheckinNumber(bookingEntity.getCarCheckinNumber())
                .build();

    }

}
