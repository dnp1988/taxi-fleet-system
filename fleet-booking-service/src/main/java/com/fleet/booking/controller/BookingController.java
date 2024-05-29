package com.fleet.booking.controller;

import com.fleet.booking.api.ApiConstants;
import com.fleet.booking.api.BookingRequest;
import com.fleet.booking.api.BookingResponse;
import com.fleet.booking.domain.entity.BookingInput;
import com.fleet.booking.domain.usecase.CreateBooking;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.PATH_BOOKINGS)
public class BookingController {

    private final CreateBooking createBooking;

    public BookingController(CreateBooking createBooking) {
        this.createBooking = createBooking;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookingResponse createBooking(@RequestBody final BookingRequest bookingRequest) {
        String id = createBooking.create(BookingInput.builder()
                        .passengers(bookingRequest.getPassengers())
                        .destinationLatitude(bookingRequest.getDestinationLatitude())
                        .destinationLongitude(bookingRequest.getDestinationLongitude())
                .build());
        return new BookingResponse(id);
    }
}
