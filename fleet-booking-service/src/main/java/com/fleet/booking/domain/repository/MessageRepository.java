package com.fleet.booking.domain.repository;

import com.fleet.message.dto.BookingMessage;

public interface MessageRepository {

    void sendBookingMessage(BookingMessage bookingMessage);
}
