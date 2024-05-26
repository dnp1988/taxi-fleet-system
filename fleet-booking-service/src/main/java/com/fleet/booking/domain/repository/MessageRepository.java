package com.fleet.booking.domain.repository;

import com.fleet.booking.domain.entity.BookingInput;

public interface MessageRepository {

    void sendBookingMessage(BookingInput bookingInput);
}
